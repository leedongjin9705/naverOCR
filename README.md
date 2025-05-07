OCR을 사용해보자!

네이버의 CLOVA OCR을 사용하여 테스트 시작

명함 인식을 기준으로 시작했습니다.(서비스는 많으니 추후 늘리거나 수정하면 될 것 같음)

명함 인식에 특화된 Document OCR을 사용해 볼 예정입니다.

강의를 들어보니 신청한다고 무작정 받을 수 있는 건 아닌 듯 합니다.

![Image](https://github.com/user-attachments/assets/b4ef85a1-e3f6-4dd8-9075-ee33909ccf3d)
OCR API를 사용하기 위해서는 OCR-API Gateway 서비스도 신청해야 합니다

![Image](https://github.com/user-attachments/assets/23457192-3fbc-4b3e-afb2-228c895f6492)

![Image](https://github.com/user-attachments/assets/d7faa81c-a000-4a07-bdee-b4ea674236ed)

![Image](https://github.com/user-attachments/assets/e2bbc373-daac-4dc1-b7ae-ac768cc8d300)
로그인 후 이용신청을 누르면 콘솔로 이동된다.

![Image](https://github.com/user-attachments/assets/dcae2d27-c695-4816-ab4a-76bb44ec305a)
Service에서 OCR을 검색해서 신청 → 도메인명과 도메인 코드를 입력하면 도메인이 생성된다.

![Image](https://github.com/user-attachments/assets/0d944653-67e3-493d-aa6c-190e193bc052)

![Image](https://github.com/user-attachments/assets/1f854fa6-8872-40d1-a3b4-afeaf39ee687)
데모를 누르면 테스트가 가능하다.

![Image](https://github.com/user-attachments/assets/ffa13c7f-feb6-48d7-a9a2-6065dd6aad25)
정확도는 위와 같은데, 번호, 이름, 이메일, 주소는 대체적으로 잘 뽑히고, 나머지는 잘 안나왔지만,

로고와 로고 아래의 작은 글씨에서 가져온 듯 하여 이해가 되는 범위인 듯 합니다.

 

유료버전은 뭔가 추가 기능이 있는 듯 합니다.

https://www.ncloud.com/product/aiService/ocr#pricing 

위 링크는 서비스별 요금정보입니다.

![Image](https://github.com/user-attachments/assets/439c73e0-f180-45aa-9f8c-2aba30601839)
특화 모델(명함으로 신청)은 따로 신청해야합니다.

![Image](https://github.com/user-attachments/assets/17ddd245-6fd2-478a-8bbe-1c36826f1661)

기능을 사용하기 위해서는 API GATEWAY 연동이 필수입니다.

 API GATEWAY 서비스를 신청하면 자동으로 연동이 가능합니다.
https://api.ncloud-docs.com/docs/ai-application-service-ocr-ocrdocumentocr
 지금은 명함 Document Ocr을 사용하기 때문에 위의 페이지를 참고하면 됩니다.

![Image](https://github.com/user-attachments/assets/3b86465b-2771-414d-ab08-73a6aaf3aef7)

두 가지 경우가 있는데, 사진을 업로드해서 데이터를 받아오는 경우에는 form-data를 사용한다고 합니다.

![Image](https://github.com/user-attachments/assets/00c989e3-8e6c-4308-bb1f-7c1db399b0ca)

코드는 따로 code에 첨부하였습니다.

감사합니다.

