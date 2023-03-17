function delFruit(fid) {
    if(confirm('sure delete?')){
        window.location.href='del.do?fid='+fid; //给del.do 发请求 同时吧fid的值带过去
    }
}

function page(pageNo){
    //给index 发请求 并吧pageNO 带过去
    window.location.href='index?pageNo='+pageNo;
}