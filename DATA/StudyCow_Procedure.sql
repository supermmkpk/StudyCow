DELIMITER //
-- 1. 일일 유저 공부시간 정산
CREATE PROCEDURE P_DAILY_USER_STUDY(IN V_PROC_DATE DATE)
BEGIN
	CREATE TEMPORARY TABLE TEMP_USER_STUDY AS
    SELECT
		USER_ID,
        STUDY_DATE AS PROC_DATE,
        SUM(STUDY_TIME) AS SUM_STUDY_TIME
	FROM T_IN_LOG
    WHERE STUDY_DATE = V_PROC_DATE
    GROUP BY USER_ID, STUDY_DATE;

	INSERT INTO T_PROC_USER_STUDY(USER_ID, PROC_DATE, SUM_STUDY_TIME)
    SELECT USER_ID, PROC_DATE, SUM_STUDY_TIME FROM TEMP_USER_STUDY
    ON DUPLICATE KEY UPDATE SUM_STUDY_TIME = VALUES(SUM_STUDY_TIME);
    
    DROP TEMPORARY TABLE TEMP_USER_STUDY;
END //


DELIMITER //
-- 2. 일일 방 공부시간 정산
CREATE PROCEDURE P_DAILY_ROOM_STUDY(IN V_PROC_DATE DATE)
BEGIN
	CREATE TEMPORARY TABLE TEMP_ROOM_STUDY AS
    SELECT
		ROOM_ID,
        STUDY_DATE AS PROC_DATE,
        SUM(STUDY_TIME) AS SUM_ROOM_TIME
	FROM T_IN_LOG
    WHERE STUDY_DATE = V_PROC_DATE
    GROUP BY ROOM_ID, STUDY_DATE;

	INSERT INTO T_PROC_ROOM_STUDY(ROOM_ID, PROC_DATE, SUM_ROOM_TIME)
    SELECT ROOM_ID, PROC_DATE, SUM_ROOM_TIME FROM TEMP_ROOM_STUDY
    ON DUPLICATE KEY UPDATE SUM_ROOM_TIME = VALUES(SUM_ROOM_TIME);
    
    DROP TEMPORARY TABLE TEMP_ROOM_STUDY;
END //

DELIMITER //
-- 3. 일일 정산데이터에 따른 exp 부여
CREATE PROCEDURE P_DAILY_USER_EXP(IN V_PROC_DATE DATE)
BEGIN
	-- TEMP TABLE 생성 및 유저 공부시간 합산값에 따라 EXP 부여
	CREATE TEMPORARY TABLE TEMP_USER_EXP AS
    SELECT
		USER_ID,
        NOW() AS GET_DATE,
        SUM_STUDY_TIME AS GET_AMOUNT
	FROM T_PROC_USER_STUDY
    WHERE PROC_DATE = V_PROC_DATE;
    
    -- TEMP TABLE에 유저 RANK에 따라 EXP 부여
    INSERT INTO TEMP_USER_EXP(USER_ID, GET_DATE, GET_AMOUNT)
	SELECT USER_ID, NOW(),
	1050 - (rank() OVER (ORDER BY SUM_STUDY_TIME DESC)) * 50
	FROM T_PROC_USER_STUDY
	WHERE PROC_DATE = V_PROC_DATE
	LIMIT 10;

	-- TEMP TABLE에서 T_EXP_LOG 테이블로 INSERT
	INSERT INTO T_EXP_LOG(USER_ID, GET_DATE, GET_AMOUNT)
    SELECT USER_ID, GET_DATE, GET_AMOUNT FROM TEMP_USER_EXP;
    
    DROP TEMPORARY TABLE TEMP_USER_EXP;
END //

DELIMITER //
-- 0. 일일 정산프로시저 호출
CREATE PROCEDURE P_DAILY_CALL_PROCEDURE(IN V_PROC_DATE DATE)
BEGIN
	START TRANSACTION;
	-- 1. 유저 일일 공부시간 정산
    CALL P_DAILY_USER_STUDY(V_PROC_DATE);
    
    -- 2. 방 일일 공부시간 정산
    CALL P_DAILY_ROOM_STUDY(V_PROC_DATE);
    
    -- 3. 정산 후 EXP 부여
    CALL P_DAILY_USER_EXP(V_PROC_DATE);
    
    COMMIT;
END //

