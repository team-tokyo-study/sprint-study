/*  1. 박스 두 개 만들기
    2. 드랍다운 리스트 만들기
    3. 환율정보 가져오기
    4. 드랍다운 리스트에서 아이템 선택 시 아이템 변경
    5. 금액 입력 시 환율 금액 표시
    6. 드랍다운 리스트에서 아이템 선택 시 다시 그 단위 기준으로 환전 정보 표시
    7. 숫자 단위를 한국어로 표기
    8. 아래 박스에서 금액을 입력해도 위 박스에 환율 금액이 적용되어 표시
*/

let currencyRatio = {
    USD: {
        USD: 1,
        JPY: 137.31,
        KRW: 1310.2,
        unit: "Dollar",
        img: "https://cdn-icons-png.flaticon.com/512/555/555526.png",
    },
    JPY: {
        USD: 0.0073,
        JPY: 1,
        KRW: 9.54,
        unit: "Yen",
    },
    KRW: {
        USD: 0.00076,
        JPY: 0.1,
        KRW: 1,
        unit: "Won",
        img: "https://cdn.countryflags.com/thumbs/south-korea/flag-400.png",
    }
}; 

let fromCurrency = 'USD'
let toCurrency = 'USD'

document
    .querySelectorAll("#from-currency-list a") // index.html의 from-currency-list의 a태그에게 이벤트를 줄 것
    .forEach((menu) => menu.addEventListener("click", function(){
        // 1. 버튼을 가져온다 → document.getElementById("from-button") 까지
        // 2. 버튼의 값을 바꾼다
        document.getElementById("from-button").textContent = this.textContent; // from-button의 텍스트를 내가 선택한 텍스트(this.textContent)로 바꾸어 준다 (KRW 선택하면 버튼이 KRW로 바뀌게)
        
        // 3. 선택된 환율값을 저장변수에 저장해준다
        fromCurrency = this.textContent;
        convert()
    })); 

document
    .querySelectorAll("#to-currency-list a")
    .forEach((menu) => menu.addEventListener("click", function(){
        document.getElementById("to-button").textContent = this.textContent;
        toCurrency = this.textContent;
        convert()
    }));


// 1. 키를 입력하는 순간 : main.js에서 convert함수를 정의 하고 index.html에서 onkeyup convert함수를 사용 
// 2. 환전이 되어
// 3. 환전된 값이 표시된다

function convert(){
    // 환전의 로직 (얼마를 환전할것인지, 가지고있는 돈의 단위가 무엇인지, 환전하고자 하는 돈의 단위가 무엇인지)
    // 현재자금 * 환율 = 환전할 금액
    let amount = document.getElementById("from-input").value; // 금액 입력 받기
        console.log("돈은 ", amount);
    let convertedAmount = amount * currencyRatio[fromCurrency][toCurrency];
        console.log("환전 결과 ", convertedAmount)
    document.getElementById("to-input").value = convertedAmount;
}


// 드랍다운 리스트의 값이 바뀔 때마다 환전을 다시 한다