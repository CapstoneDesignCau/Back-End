사진을 잘 찍지 못하는 사람들을 위해 AI를 이용해 사진 분석 및 피드백을 제공하고 교육자료와 사진 관련 커뮤니케이션을 할 수 있는 웹 사이트를 개발하였습니다.

- Springboot 3와 SpringSecurity 6, JAVA 17을 이용하였습니다.

application-dev.yml에 아래와 같은 코드를 형식에 맞게 입력해야 정상적으로 서버를 시작할 수 있습니다.

spring:
  mvc:
    pathmatch:
      matching-strategy: ant_path_matcher

  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: ‘AWS RDS 주소’
    username: ‘DB ID’
    password: ‘DB 비번’

  servlet:
    multipart:
      max-file-size: 20MB
      max-request-size: 20MB
      enabled: true

  jwt:
    secret: secretCapstone01JWT
    expired-time: 3600000 # 1시간(1000 * 60 * 60)

cloud:
  aws:
    s3:
      bucket: ‘AWS S3 버킷 이름‘
    credentials:
      access-key: ‘S3 access key’
      secret-key: ‘S3 secret key’
    region:
      static: ap-northeast-2
    stack:
      auto: false

default:
  profile:
    image:
      url: ‘S3에 저장된 AWS 주소’
