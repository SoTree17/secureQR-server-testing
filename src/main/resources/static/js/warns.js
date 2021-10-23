function imgPopup(){
    const url = '/qrImg/Server-Test.png'
    let img = new Image();
    img.src = url;
    const img_width = img.width;
    const win_width = img.width + 25;
    const img_height = img.height;
    const left = (screen.width / 2) - (img_width / 2);
    const top = (screen.height / 2) - (img_height - 2);
    let OpenWindow = window.open(url, '_blank', 'width=' + img_width + ', height=' + img_height + ', menubars=no, scrollbars=auto,left=' + left + ',top=' + top);
    OpenWindow.resizeTo(250, 250)
}

function formValidCheck() {
    let content = document.userForm;
    let authURL = content.authUrl.value;
    let data = content.data.value;
    let c_index = content.c_index.value;
    let width = content.width.value;
    let height = content.height.value;

    if (!authURL || !data || !c_index || !width || !height) {
        alert("빈칸을 채워주세요.")
    } else {
        content.submit();
       /* sleep(2000);*/
        imgPopup();
        //setTimeout(function(){imgPopup('/qrImg/Server-Test.png')},500);
    }

}
/*function sleep(delay){
    var start = new Date().getTime();
    while (new Date().getTime() < start + delay);
}*/

/*
function imgEvent() {
    const url = '/qrImg/Server-Test.png'
    let img = new Image();
    img.src = url;
    const img_width = img.width;
    const win_width = img.width + 25;
    const img_height = img.height;
    const left = (screen.width / 2) - (img_width / 2);
    const top = (screen.height / 2) - (img_height - 2);
    let OpenWindow = window.open(url, '_blank', 'width=' + img_width + ', height=' + img_height + ', menubars=no, scrollbars=auto,left=' + left + ',top=' + top);
    OpenWindow.resizeTo(250, 250);

}*/
