function delFruit(fid) {
    if(confirm('sure delete?')){
        window.location.href='del.do?fid='+fid; //��del.do ������ ͬʱ��fid��ֵ����ȥ
    }
}

function page(pageNo){
    //��index ������ ����pageNO ����ȥ
    window.location.href='index?pageNo='+pageNo;
}