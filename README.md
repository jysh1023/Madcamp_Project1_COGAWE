# Mad Camp 1주차 과제 🔥

Download APK File: [COGAWE][apklink]

[apklink]: app/release/app-release.apk

### 공통과제 I (6/29 ~ 7/5) - 탭 구조를 활용한 안드로이드 앱 제작
#### 📌 목적
- 서로 함께 공통의 과제를 함으로써, 개발에 빠르게 익숙해지기

#### 🌟 결과물
- 세 개의 탭이 존재하는 안드로이드 앱
  - TAB 1: Contact
    - 나의 연락처 구축. 휴대폰의 연락처 데이터를 활용하거나, JSON 형식을 이용해서 임의의 연락처 데이터를 구축
  - TAB 2: Gallery
    -  나만의 이미지 갤러리 구축. 이미지는 대략 20개 정도.
  - TAB 3: Weather
    - 자유 주제

👥 팀원
-------------
- 양성현 (카이스트 전산학부)
- 김준서 (한양대학교 컴퓨터소프트웨어학부)


💻 개발 환경
-------------
- OS : Android
- SDK : 33
- Langauge : Kotlin
- IDE : Android Studio
- Target Device: Galaxy S7

<br>

💫 결과물
-------------


### TAB 1 : 연락처 📞
#### Features
> - `assets/contact.json`에 저장된 연락처 불러오기
> - Main 화면 : 연락처 검색하기 (이름 혹은 전화번호로 검색 가능)
> - Detail 화면: 연락처 상세 정보 (이름, 전화번호, 학교, 관심사) & 전화걸기
> - New contact 화면: 연락처 추가하기 (비워진 필드가 있을 경우 Toast로 경고 메시지 표시)

<img src="/Screenshots/Contact_main.png" width="30%" alt="Contact Main"></img>
<img src="/Screenshots/Contact_detail.png" width="30%"  alt="Contact Detail"></img>
<img src="/Screenshots/Contact_new.png" width="30%" alt="Contact New"></img>
<img src="/Screenshots/Contact.gif" width="30%" alt="Contact GIF"></img>

<br>

### TAB 2 : 갤러리 🌃
#### Features
> - `assets/gallery.json`에 저장되어 있는 이미지 주소를 기반으로 사진을 보여주는 갤러리
> - Preview 화면: 사진 터치(해당 사진의 detail을 보여주는 화면으로 이동)
> - Detail 화면: pinch zoom(확대/축소), double tap(이미지 사이즈 리셋), 좌우 swipe(이전/이후 이미지로 이동), 뒤로가기(preview 화면으로 돌아가기)

<img src="/Screenshots/Gallery_main.png" width="30%" alt="Gallery Main"></img>
<img src="/Screenshots/Gallery.gif" width="30%" alt="Gallery GIF"></img>

<br>

### TAB 3 : 날씨🌈
#### Features
> - 현재 날씨 실황 (기온, 하늘상태, 풍속, 습도)
> - 단기 날씨 예보 (추후 6시간, 기온, 하늘상태)
> - 새로고침하여 현재 정보 받아오기 (마지막으로 새로고침한 시각 표시)

<img src="/Screenshots/Weather_main.png" width="30%" alt="Weather Main"></img>
<img src="/Screenshots/Weather.gif" width="30%" alt="Weather GIF"></img>
