// // exchange 테스트용 (수정해야됨)
// let currencyRatio={HKD:{USD:0.12817062073031618}, CHF:{EUR:1.0112245929821013}, MXN:{USD:0.06025112669606921}, EUR:{CHF:0.9889, JPY:169.483, GBP:0.8559, CAD:1.4794, USD:1.0877, HUF:386.78, SEK:11.6388}, MYR:{USD:0.21344717182497333}, CAD:{EUR:0.675949709341625}, USD:{HKD:7.8021, MXN:16.5972, EUR:0.919371150133309, MYR:4.685, ZAR:18.1591, INR:83.279, CNY:7.2288, THB:36.07, AUD:1.4918693122482471, SGD:1.3454, JPY:155.863, GBP:0.7871536523929471, IDR:15949.0, NZD:1.6307893020221789, PHP:57.745, RUB:91.05}, ZAR:{USD:0.05506880847619101}, INR:{USD:0.012007829104576184}, THB:{USD:0.02772387025228722}, CNY:{USD:0.13833554670208056}, AUD:{USD:0.6703}, SGD:{USD:0.7432733759476736}, JPY:{EUR:0.005900296784928281, GBP:0.005050556066222891, USD:0.006415890878527938}, GBP:{JPY:197.998, EUR:1.168360789811894, USD:1.2704}, IDR:{USD:6.269985579033168E-5}, SEK:{EUR:0.08591951060246761}, HUF:{EUR:0.0025854490925073686}, PHP:{USD:0.017317516668109795}, NZD:{USD:0.6132}, RUB:{USD:0.010982976386600769}}
// var unitWords = ["", "만", "억", "조", "경"]; 
// var splitUnit = 10000;
// let fromCurrency = "USD";
// let toCurrency = "USD";


// document.querySelectorAll("#from-currency-list a").forEach((menu)=>menu.addEventListener("click",function(){
//     document.getElementById("from-button").innerHTML=this.innerHTML;
//     fromCurrency = this.textContent;
//     // console.log("fromcurrency는 ",fromCurrency);
//     convert();
// }));

// document.querySelectorAll("#to-currency-list a").forEach((menu)=>menu.addEventListener("click",function(){
//     document.getElementById("to-button").innerHTML=this.innerHTML;
//     toCurrency = this.textContent;
//     convert();
// }));


// // 환전
// function convert(){
//     //돈(입력값) * 환율 = 환전금액

//     let amount = document.getElementById("from-input").value;
//     let convertedAmount = amount * currencyRatio[fromCurrency][toCurrency];
//     console.log("환전 결과는 ", convertedAmount);

//     document.getElementById("to-input").value=convertedAmount;
//     readNumKorean();
// }

// function reconvert(){
//     let amount = document.getElementById("to-input").value;
//     let convertedAmount = amount * currencyRatio[toCurrency][fromCurrency];

//     document.getElementById("from-input").value=convertedAmount;

// }

// function readNumKorean(){
//     document.getElementById("fromNumToKorea").textContent=
//     readNum(document.getElementById("from-input").value)+
//     currencyRatio[fromCurrency].unit;

//     document.getElementById("toNumToKorea").textContent=
//     readNum(document.getElementById("to-input").value)+
//     currencyRatio[toCurrency].unit
// }

// // 숫자단위
// function readNum(num){
//     let resultString = "";
//     let resultArray  = [];

//     // 만단위로끊어내는 for문
//     for(let i=0;i<unitWords.length;i++){
//         let unitResult=(num%Math.pow(splitUnit, i+1))/Math.pow(splitUnit, i);
//         unitResult=Math.floor(unitResult);
//         if(unitResult>0){
//             resultArray[i] = unitResult;
//         }
//     }

//     for(let i=0;i<resultArray.length;i++){
//         if(!resultArray[i]) continue;
//         resultString=String(resultArray[i])+unitWords[i]+resultString;
//     }
//     return resultString;
// }

const money =  document.getElementById("frominput")
const first = 'AUD'
const second = 'USD'
//const first = document.getElementById("from-currency-list1").value
//const second = document.getElementById("from-currency-list2").value

frominput.addEventListener('input', function (event) {
    const query = frominput.value.trim(); // 검색어 가져오기
    if (query === '') {
        // 검색어가 비어 있는 경우 결과를 지우고 함수 종료
        clearSearchResults();
        return;
    }
    // AJAX 요청 생성
    const xhr = new XMLHttpRequest();
    xhr.open('GET', '/v1/nasdaq/converter?first=' + first + '&second=' + second + '&money=' + money, true);
    // 요청 완료 시 동작 정의
    xhr.onload = function () {
        if (xhr.status >= 200 && xhr.status < 400) {
            // 성공적으로 응답이 도착한 경우
            const data = JSON.parse(xhr.responseText);
            displaySearchResults(data);
        } else {
            // 에러가 발생한 경우
            console.error('요청 실패');
        }
    };
    // 요청 실패 시 동작 정의
    xhr.onerror = function () {
        console.error('네트워크 오류');
    };
    // 요청 전송
    xhr.send();
});
// 검색 결과를 표시하는 함수
function displaySearchResults(results) {
    searchResultsDiv.innerHTML = ''; // 결과를 표시하기 전에 이전 결과 삭제
    if (results.length === 0) {
        searchResultsDiv.innerHTML = '검색 결과가 없습니다.';
    } else {
        document.getElementById('toNumToKorea').textContent = results;
    }
}
// 검색 결과를 지우는 함수
function clearSearchResults() {
    searchResultsDiv.innerHTML = '';
}


