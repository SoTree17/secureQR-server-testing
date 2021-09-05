/*
function imgPop(url){
    let img = new Image();
    img.src = url;
    const img_width = img.width;
    const img_height = img.height;
    const tab_width = img.width + 25;
    const tab_height = img.height + 30;

    let new_tab = window.open('','_blank', 'width='+img_width+', height='+img_height+', menubars=no, scrollbars=auto');
    new_tab.document.write("<style>body{margin:0px;}</style><img src='"+url+"' width='"+tab_width+"'>");
}*/
function imgPop(){
    var img=new Image();
    img.src='/qrImg/Server-Test';
    var img_width=img.width;
    var win_width=img.width+25;
    var img_height=img.height;
    var win=img.height+30;
    var OpenWindow=window.open('','_blank', 'width='+img_width+', height='+img_height+', menubars=no, scrollbars=auto');
    OpenWindow.document.write("<style>body{margin:0px;}</style><img src='"+url+"' width='"+win_width+"'>");
}