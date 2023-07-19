# 💐 Primrose_kotlin

## 📖 프로젝트 소개
| 소개 화면(메인 화면) | 메뉴 화면 | 꽃 리스트 화면 | 꽃 검색 화면 | 꽃집 검색 화면 |
|--|--|--|--|--|
| <img width="278" alt="스크린샷 2023-07-19 오후 11 42 05" src="https://github.com/Wise-99/Primrose_kotlin/assets/90273263/96f63a35-7bd4-4c1f-85d6-b6c09840bed8"> | <img width="276" alt="스크린샷 2023-07-19 오후 11 42 16" src="https://github.com/Wise-99/Primrose_kotlin/assets/90273263/e1cf5e12-071e-4a3d-8cc8-f03eed4add9e"> |  <img width="279" alt="스크린샷 2023-07-19 오후 11 42 27" src="https://github.com/Wise-99/Primrose_kotlin/assets/90273263/5574ce78-c67f-4da6-9c83-827b88c06872"> | <img width="277" alt="스크린샷 2023-07-19 오후 11 42 56" src="https://github.com/Wise-99/Primrose_kotlin/assets/90273263/0efddb44-5dc6-4acc-a043-1016415eb9b7"> | <img width="276" alt="스크린샷 2023-07-19 오후 11 43 26" src="https://github.com/Wise-99/Primrose_kotlin/assets/90273263/2dce7b7e-b9b7-43ad-85ae-c1ad83e44fc9"> |



> 💐 Java로 개발했던 프림로즈-Primrose를 Kotlin을 이용하여 리팩토링 하였습니다. UI 변경 및 기능을 수정하였습니다.
> 
> - 사이드 네비게이션 바를 이용하여 메뉴를 선택할 수 있습니다.
> - 메뉴에는 3가지 기능이 있으며 전체 보기, 검색, 꽃집 찾기 기능이 있습니다.
> - 검색 기능 선택 시 꽃 이름, 꽃말을 검색할 수 있습니다.
> - 꽃집 찾기 기능 선택 시  네이버 지도 검색 결과 화면으로 이동하여 가까운 꽃집을 찾을 수 있습니다.

## 📄 담당 업무

- 기존에 Activity만으로 구성했던 앱을 **Fragment로 분리**하여 메뉴 기능을 **재사용**하도록 개선
- ToolBar와 TabLayout을 이용한 메뉴 구성 방식을 **DrawerLayout Navigation**을 이용한 메뉴 구성으로 전환
- **LiveData**와 **ViewModel**을 사용하여 데이터 수정 시 바로 적용할 수 있도록 구현
    - 사용자가 새로 고침할 필요 없이 Firebase의 데이터 추가 시 바로 적용되도록 개선
- **Glide를 모듈화**하여 기능을 따로 관리할 수 있도록 분리
- Google Map 대신 **WebView를 이용하여 Naver 지도**를 화면에 나타내 꽃집 검색

## 💡 문제 해결 과정

- onBackPressed() → OnBackPressedCallback()
    - API 레벨 33부터 onBackPressed()가 사용되지 않음
    - onBackPressedDispatcher()의 onBackPressedCallback()으로 대체하여 사용
 
- 꽃말 검색과 꽃 이름 검색을 나눠 놓은 코드를 개선하고자 함
    - mutableList에 Flower 객체를 받을 때 호출하는 함수를 분리하여 구현
 
- GoogleMap과 Naver의 정보 차이로 인해 GoogleMap 보다는 국내 서비스를 사용하고자 고민하게 됨
    - Naver 지도를 이용하여 꽃집 검색을 하도록 유도
    - 이를 위해 WebView로 네이버 검색 결과 화면을 띄움 
