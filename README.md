# Lingo Tutor Server

## Demo Video Link
1080p: https://drive.google.com/file/d/1h5kBud3UXMalPAtwr30qfGoaG0HC21sH/view

720p: https://drive.google.com/file/d/1sS_Jgq6VfLGUop12L-_GEk2I2IWFWGIB/view

#### For Lingo Tutor Client Setup refer: https://github.com/anantha-krish/lingo-tutor-frontend

## Tech stack used


<img class="icon" height="100" src="https://github.com/anantha-krish/lingo-tutor-backend/assets/22259160/2902a21f-656a-4ec8-b35c-16ef34cd8efa" /> &nbsp;  &nbsp; 
<img height="100" src="https://github.com/anantha-krish/lingo-tutor-backend/assets/22259160/ab6c2f99-20b5-4b27-8299-c10f48a2304c" />
<img height="100" src="https://github.com/anantha-krish/lingo-tutor-backend/assets/22259160/2faf4a8f-bc47-454e-a6f5-c0121005c05c" />
<img height="100" src="https://github.com/anantha-krish/lingo-tutor-backend/assets/22259160/9523db48-cd99-420b-a534-99af91c7f4c1" />

<ul>
  <li><b>Backend</b>: Java 21, Spring Boot 3, Gradle, Spring Cloud Gateway, Spring Cloud Netflix  Eureka Server, Spring Security, JWT, Spring Web, Spring Data JPA (Hibernate), Spring HATEOAS, OpenFeign </li>
  <li><b>Database</b>: Three Free-tier Postgres Databases hosted in Cloud Platform (https://neon.tech/)</li>
</ul>

## How to run app ?

1. Prerequisite: System should have `Java 21` and `Gradle 8.7` installed.

1. Checkout the project.
   
3. Execute `gradle --refresh-dependencies` on each gradle project folders to download the dependancies.

4. Execute `gradle bootrun` to run the project. 
   
Further refernce: https://docs.spring.io/spring-boot/docs/1.5.16.RELEASE/reference/html/using-boot-running-your-application.html

## Architecture Design

![Lingo Tutor  drawio (3)](https://github.com/anantha-krish/lingo-tutor-backend/assets/22259160/99244e7b-32ed-47cf-bd79-d59b5b026225)


| Service   | Port |
|-----------|------|
| Gateway   | 8765 |
| Eureka    | 8761 |
| Users     | 8000 |
| Languages | 8100 |
| quizzes   | 8200 |

Swagger URL `http://localhost:{port}/swagger-ui/index.html`

## API Routes
- http://localhost:8765/users
- http://localhost:8765/languages
- http://localhost:8765/quizzes

![image](https://github.com/anantha-krish/lingo-tutor-backend/assets/22259160/4558c402-e347-47c8-88b5-fdc0c1097487)

![image](https://github.com/anantha-krish/lingo-tutor-backend/assets/22259160/f7607885-0650-48ff-a074-0f7f243ec5df)

![image](https://github.com/anantha-krish/lingo-tutor-backend/assets/22259160/cef04cf7-72a9-4f98-9161-200ecfff8602)
