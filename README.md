# productmanage-Rest-API

### API 명세서

### productmanage-Rest-API
해당 API는 JPA를 이용한 REST API입니다. 구매가능한 상품 및 전체상품 조회, 장바구니 기능, 주문 및 결제 기능을 제공하고 있습니다, 결제 기능은 MOCK API를 사용했습니다.

### 기본정보
- API 이름 : allra-backend API
- 버전 : v1
- 기본 URL : http://localhost:8088/api/v1

```
API 기술 스택

Framework : Spring boot 3.4.3
Language : Java 17
Database : MariaDB 10.11.11
ORM : Spring Data JPA, QueryDSL
Tool : Visual Studio Code

Mock API : beeceptor
```

### 엔드포인트
(해당 문서의 응답예시는 모두 실제로 존재하지 않는 더미데이터 입니다.)

### 기능별 목록
- [상품관련 기능](#상품관련-기능)
- [장바구니 관련 기능](#장바구니-관련-기능)
- [주문관련 기능](#주문관련-기능)
- [결제관련 기능](#결제관련-기능)
- [성공메세지](#성공메세지)
- [에러메세지](#에러메세지)



---
### 상품관련 기능

#### **GET** /product
- 설명 : 재고가 0개 이상인 판매 가능 상품 목록 조회
- 요청 파라미터 : 없음

**응답 파라미터**
| 파라미터 | 타입 | 설명 | 
| --- | --- | --- |
| id | Long | 상품 고유 번호 | 
| name | String | 상품 이름 |
| description | String | 상품 설명 | 
| price | Long | 상품 가격 | 
| stock | Long | 상품의 현재 재고 | 

응답 예시
```json
{
    "code": 200,
    "data": [
        {
            "id": 1,
            "name": "사과",
            "description": "충주에서 생산한 신선한 사과",
            "price": 1500,
            "stock": 30
        },
        {
            "id": 2,
            "name": "바나나",
            "description": "마다가스카르산 바나나",
            "price": 5000,
            "stock": 30
        },
        {
            "id": 3,
            "name": "접시",
            "description": "이천에서 만든 접시",
            "price": 9000,
            "stock": 30
        }
    ],
    "message": "successfully get"
}
```

<br>

#### **GET** /product/{productid}
- 설명 : 특정 ID의 상품 조회
- 요청 파라미터 
    - productid (필수) : 상품 번호 

**응답 파라미터**
| 파라미터 | 타입 | 설명 | 
| --- | --- | --- |
| id | Long | 상품 고유 번호 | 
| name | String | 상품 이름 |
| description | String | 상품 설명 | 
| price | Long | 상품 가격 | 
| stock | Long | 상품의 현재 재고 | 

응답 예시
```json
{
    "code": 200,
    "data": {
        "id": 1,
        "name": "사과",
        "description": "충주에서 생산한 신선한 사과",
        "price": 1500,
        "stock": 30
    },
    "message": "successfully get"
}
```

<br>

#### **GET** /products
- 설명 : 재고량 관계없이 상품 전체 목록 조회
- 요청 파라미터 : 없음

**응답 파라미터**
| 파라미터 | 타입 | 설명 | 
| --- | --- | --- |
| id | Long | 상품 고유 번호 | 
| name | String | 상품 이름 |
| description | String | 상품 설명 | 
| price | Long | 상품 가격 | 
| stock | Long | 상품의 현재 재고 | 

응답 예시
```json
{
    "code": 200,
    "data": [
        {
            "id": 1,
            "name": "사과",
            "description": "충주에서 생산한 신선한 사과",
            "price": 1500,
            "stock": 30
        },
        {
            "id": 2,
            "name": "바나나",
            "description": "마다가스카르산 바나나",
            "price": 5000,
            "stock": 30
        },
        {
            "id": 3,
            "name": "접시",
            "description": "이천에서 만든 접시",
            "price": 9000,
            "stock": 30
        },
        {
            "id": 4,
            "name": "오렌지",
            "description": "플로리다에서 따온 오렌지",
            "price": 3000,
            "stock": 0
        }
    ],
    "message": "successfully get"
}
```
[목록으로 이동하기](#기능별-목록)

<br>

---
### 장바구니 관련 기능

#### **GET** /cart/{customerid}
- 설명 : 입력한 고객번호의 장바구니 목록 조회
- 요청 파라미터 
   - customerid (필수) : 고객 번호

**응답 파라미터**
| 파라미터 | 타입 | 설명 | 
| --- | --- | --- |
| id | Long | 장바구니 고유 번호 | 
| customerName | String | 주문자 이름 |
| productName| String| 상품이름 | 
| productPrice| Long | 상품 가격 | 
| cartStock| Long | 장바구니에 등록된 재고 | 
| productAllPrice| Long | 상품 전체 가격 | 

응답 예시
```json
{
    "code": 200,
    "data": [
        {
            "id": 1,
            "customerName": "박영희",
            "productName": "사과",
            "productPrice": 1500,
            "cartStock": 10,
            "productAllPrice": 15000
        }
    ],
    "message": "successfully get"
}
```

<br>

#### **POST** /cart?customerid=[]&productid=[]&stock=[]
- 설명 : 재고량 관계없이 상품 전체 목록 조회
- 요청 파라미터 
   - customerid **(Integer)** : 고객 번호 (필수)
   - productid **(Integer)** : 상품 번호 (필수)
   - stock **(Integer)** : 재고 (필수)

응답 예시
```json
{
    "code": 201,
    "message": "successfully post"
}
```

<br>

#### **PATCH** /cart/{customerid}?cartid=[]&stock=[]
- 설명 : 장바구니 1건 재고 수정
- 요청 파라미터 
   - customerid **(Integer)** : 고객 번호 (필수)
   - cartid **(Integer)** : 장바구니 번호 (필수)
   - stock **(Integer)** : 재고 변경값 (필수)

응답 예시
```json
{
    "code": 200,
    "message": "successfully edit"
}
```

<br>

#### **DELETE** /cart/{customerid}?cartid=[]
- 설명 : 장바구니 1건 재고 수정
- 요청 파라미터 
   - customerid **(Integer)** : 고객 번호 (필수) 
   - cartid **(Integer)** : 장바구니 번호 (필수)

응답 예시
```json
{
    "code": 200,
    "message": "successfully delete"
}
```

[목록으로 이동하기](#기능별-목록)

<br>

---
### 주문관련 기능

#### **GET** /order/{customerid}
- 설명 : 입력한 고객번호의 주문 목록 조회
- 요청 파라미터 
   - customerid **(Integer)** : 고객 번호 (필수)

**응답 파라미터**
| 파라미터 | 타입 | 설명 | 
| --- | --- | --- |
| orderId | String | 주문 코드 번호 | 
| customerName | String | 주문자 이름 |
| paymentYn | String | 현재 결제 상태 | 
|  cartList |  
| id| Long | 장바구니 번호 | 
| productName | String | 상품 이름  | 
| productPrice| Long | 상품 개당 가격 | 
| cartStock| Long | 장바구니에 등록된 재고 | 
| productAllPrice| Long | 상품 전체 가격 | 

응답 예시
```json
{
    "code": 200,
    "data": [
        {
            "orderId": "OD2CA1",
            "customerName": "박영희",
            "paymentYn": "결제 대기",
            "cartList": [
                {
                    "id": 1,
                    "productName": "사과",
                    "productPrice": 1500,
                    "cartStock": 10,
                    "productAllPrice": 15000
                }
            ]
        }
    ],
    "message": "successfully get"
}
```

<br>

#### **POST** /order?customerid=[]
- 설명 : 장바구니의 모든목록을 주문
- 요청 파라미터 
   - customerid **(Integer)** : 고객 번호 (필수)

응답 예시
```json
{
    "code": 200,
    "message": "successfully post"
}
```
[목록으로 이동하기](#기능별-목록)

<br>

---
### 결제관련 기능

#### **GET** /payment/{customerid}
- 설명 : 특정 고객의 결제 내역을 조회
- 요청 파라미터 
   - customerid **(Integer)** : 고객 번호 (필수)

**응답 파라미터**
| 파라미터 | 타입 | 설명 | 
| --- | --- | --- |
| id | Long | 결제 번호 | 
| name| String | 고객 이름 | 
| orderCode | String | 주문 코드 번호 | 
| amount| Long | 지불한 금액 | 
| successYn| String | 결제시도 후 성공 여부 | 
| failReason | String | 결제 실패시 원인 메세지 (결제 성공시 null / 결제 실패시 원인 출력) | 
| createDt| LocalDateTime | 결제 시도 시간 | 

응답 예시
```json
{
    "code": 200,
    "data": [
        {
            "id": 1,
            "name": "박영희",
            "orderCode": "OD2CA1",
            "amount": 20000,
            "successYn": "결제 성공",
            "failReason": null,
            "createDt": "2025-03-01T21:02:45.500368"
        }
    ],
    "message": "successfully get"
}
```

<br>

#### **POST** /payment?customerid=[]?orderid=[]&amount=[]
- 설명 : 주문목록의 특정 주문을 외부 결제 API를 통해 결제
- 요청 파라미터 
   - customerid **(Integer)** : 고객 번호 (필수)
   - orderid **(String)** : 주문 코드 번호 (필수)
   - amount **(Integer)** : 지불한 금액 (필수)
   
응답 예시
```json
{
    "code": 200,
    "message": "successfully post"
}
```


[목록으로 이동하기](#기능별-목록)

<br>
<br>

### 성공메세지
API에 요청을 하여 정상적으로 완료되면 다음과 같은 응답을 받습니다.

| HTTP STATUS | 코드 | 메세지 | 설명 |
| --- | --- | --- | --- |
| OK | 200 | successfully get | 상품조회 및 장바구니 조회등 조회 관련 요청 후 성공하면 출력되는 메세지입니다. |
| CREATED | 201 | successfully post | 장바구니 물건 생성 및 주문생성등 생성 관련 요청 후 완료되면 출력되는 메세지입니다. |
| OK | 200 | successfully edit | 장바구니 물건 재고 수정 및 수정 관련 요청 후 완료되면 출력되는 메세지입니다. |
| OK | 200 | successfully delete | 장바구니 내용 삭제 등 삭제 관련 요청 후 완료되면 출력되는 메세지입니다.  |

[목록으로 이동하기](#기능별-목록)

<br>

### 에러메세지

클라이언트 요청 관련 오류 응답메세지
| HTTP STATUS | 코드 | 메세지 | 설명 |
| --- | --- | --- | --- |
| BAD_REQUEST | 400 | A required parameter is missing. | 필수 파라미터가 누락되는 경우 출력되는 메세지입니다. |
| BAD_REQUEST | 400 | The parameter type is incorrect. | 파라미터를 명시된 타입 이외의 타입을 입력한 경우에 출력되는 메세지입니다. |
| NOT_FOUND | 404 | The request does not exist. | 존재하지 않는 요청을 보냈을 때 출력되는 메세지입니다. |
| METHOD_NOT_ALLOWED | 405 | This method is not allowed. | API 문서와 다른 HTTP 타입으로 요청했을 때 출력되는 메세지입니다. |

상품관련 오류 응답메세지
| HTTP STATUS | 코드 | 메세지 | 설명 |
| --- | --- | --- | --- |
| NOT_FOUND | 404 | This product does not exist. | 특정 상품번호로 검색했을 때 결과가 없는 경우 출력되는 메세지입니다. | 
| NOT_FOUND | 404 | A required parameter is missing. | 필수 파라미터가 누락되는 경우 출력되는 메세지입니다. |
| NOT_FOUND | 404 | There are currently no products available for purchase. | 재고가 충분한 상품중에 구매 가능한 상품이 없는경우 출력되는 메세지입니다. | 

고객관련 오류 응답메세지
| HTTP STATUS | 코드 | 메세지 | 설명 |
| --- | --- | --- | --- |
| NOT_FOUND | 404 | This customer does not exist. | 고객 번호를 파라미터로 요청 후 결과가 없는 경우 출력되는 메세지입니다. |
 
장바구니 관련 오류 응답메세지
| HTTP STATUS | 코드 | 메세지 | 설명 |
| --- | --- | --- | --- |
| NOT_FOUND | 404 | There is not enough stock. | 기존 상품의 재고보다 많은 재고를 요청한 경우 출력되는 메세지입니다. |
| NOT_FOUND | 404 | There are no items in your shopping cart. | 장바구니에 물건이 존재하지 않는경우 출력되는 메세지입니다. |
| NOT_FOUND | 404 | The product does not exist in your shopping cart. | 장바구니의 상품중 상품목록에 없는 상품이 등록되어있는 경우에 출력되는 메세지입니다. |

주문관련 오류 응답메세지
| HTTP STATUS | 코드 | 메세지 | 설명 |
| --- | --- | --- | --- |
| NOT_FOUND | 404 | The order list does not exist. | 주문목록이 존재하지 않는경우 출력되는 메세지입니다. |
| NOT_FOUND | 404 | All items in your cart are out of stock. | 장바구니의 모든상품이 재고량을 초과하는 경우출력되는 메세지입니다. |

결제관련 오류 응답메세지
| HTTP STATUS | 코드 | 메세지 | 설명 |
| --- | --- | --- | --- |
| NOT_FOUND | 404 | The payment list does not exist. | 결제목록이 존재하지 않는경우 출력되는 메세지입니다. |
| NOT_FOUND | 404 | This order has already been paid. | 이미 결재가 마친 주문인 경우 출력되는 메세지입니다. |
| NOT_FOUND | 404 | The amount you paid is not enough. | 결제시 입력한 금액이 주문금액보다 작으면 출력되는 메세지입니다. |
| NOT_FOUND | 404 | There are no orders matching your order number. | 주문번호와 일치하는 주문이 없는 경우 출력되는 메세지입니다. |

[목록으로 이동하기](#기능별-목록)
