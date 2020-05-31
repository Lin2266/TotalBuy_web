
//沒加方法畫面沒出現就顯示訊息
//alert("Welcome to TotaBuy");
//等畫面出現完在顯示內容
var loadTime = new Date();//全域變數global var
function onloadHandler() {
    alert("Welcome to TotaBuy");
}
//function涵式handler處理器
function helooHandler() {
    //變數宣告，var替代型別
    //建立現在的時間物件
    var now = new Date();//區域變數local var
    //document網頁指html的body
    var nameInput = document.getElementById("name");
    var welMsg = "歡迎~" + nameInput.value + "!<br>載入時間是:" +loadTime+"<br>現在是:" + now;
    var helloDiv = document.getElementById("helloDiv");
    //div要用innerHTML屬性(有前後標籤中間的內容)
    helloDiv.innerHTML = welMsg;
//alert警告(內容)
    //alert("您好，載入時間是"+today+"現在是:"+now);
    //now = 100;
    //alert(now);//alert(1+2)
}


