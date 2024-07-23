import React from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import { Carousel } from "react-bootstrap";
import CarouselContents from "../components/CarouselContents";

const Main_Unlogin = () => {
  return (
    <Carousel fade interval={1200}>
      <Carousel.Item>
        <CarouselContents
          src="./src/assets/BG01.png"
          alt="First slide"
          text="당신의 캐릭터를, 성장시키는 즐거움"
          left="40%"
          top="400px"
        />
      </Carousel.Item>
      <Carousel.Item>
        <CarouselContents
          src="./src/assets/BG02.png"
          alt="Second slide"
          text="해야 하는 todo, check-list로 확인"
          left="100%"
          top="400px"
        />
      </Carousel.Item>
      <Carousel.Item>
        <CarouselContents
          src="./src/assets/BG03.png"
          alt="Third slide"
          text="성적 분석 기능을 통한, 나의 성장 확인 가능"
          left="90%"
          top="150px"
        />
      </Carousel.Item>
      <Carousel.Item>
        <CarouselContents
          src="./src/assets/BG04.png"
          alt="Forth slide"
          text="Study with me!, 온라인으로 함께하는 공부, 친구들과의 시간 경쟁"
          left="40%"
          top="150px"
        />
      </Carousel.Item>
    </Carousel>
  );
};

export default Main_Unlogin;
