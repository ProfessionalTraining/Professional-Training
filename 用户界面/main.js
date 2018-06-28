window.onload=function(){
    var photonum=1;
    var othumbnail=document.getElementById("thumbnail");
    var nav1=document.getElementById("tab1");
    var nav2=document.getElementById("tab2");
    var nav3=document.getElementById("tab3");
    var nav4=document.getElementById("tab4");
    var nav5=document.getElementById("tab5");
    var nav6=document.getElementById("tab6");
    function changephoto(){
        var num="photo";
        var tabnum="tab";
        var hidenum="";
        var activenum="";
        var hidetabnum="";
        var activetabnum="";
        if(photonum<6){
            hidenum=num+photonum.toString();
            activenum=num+(photonum+1).toString();
            hidetabnum=tabnum+photonum.toString();
            activetabnum=tabnum+(photonum+1).toString();
            var hidephoto=document.getElementById(hidenum);
            var activephoto=document.getElementById(activenum);
            hidephoto.className="slide-item";
            activephoto.className="slide-item slide-item-active";
            var hidetab=document.getElementById(hidetabnum);
            var activetab=document.getElementById(activetabnum);
            hidetab.className="slide-tab-item";
            activetab.className="slide-tab-item active";
            photonum++;
        }
        else if(photonum==6){
            var hidephoto=document.getElementById("photo6");
            var activephoto=document.getElementById("photo1");
            hidephoto.className="slide-item";
            activephoto.className="slide-item slide-item-active";
            var hidetab=document.getElementById("tab6");
            var activetab=document.getElementById("tab1");
            hidetab.className="slide-tab-item";
            activetab.className="slide-tab-item active";
            photonum=1;
        }
    }
    othumbnail.addEventListener('mouseover',function(){
        clearInterval(timer);
    });
    othumbnail.addEventListener('mouseout',function(){
        timer=setInterval(changephoto,5000);
    });
    nav1.addEventListener('mouseover',function(){
        photonum=6;
        for(var i=1;i<7;++i){
            document.getElementById("photo"+i.toString()).className="slide-item";
            document.getElementById("tab"+i.toString()).className="slide-tab-item";
        }
        changephoto();
    },false)
    nav2.addEventListener('mouseover',function(){
        photonum=1;
        for(var i=1;i<7;++i){
            document.getElementById("photo"+i.toString()).className="slide-item";
            document.getElementById("tab"+i.toString()).className="slide-tab-item";
        }
        changephoto();
    },false)
    nav3.addEventListener('mouseover',function(){
        photonum=2;
        for(var i=1;i<7;++i){
            document.getElementById("photo"+i.toString()).className="slide-item";
            document.getElementById("tab"+i.toString()).className="slide-tab-item";
        }
        changephoto();
    },false)
    nav4.addEventListener('mouseover',function(){
        photonum=3;
        for(var i=1;i<7;++i){
            document.getElementById("photo"+i.toString()).className="slide-item";
            document.getElementById("tab"+i.toString()).className="slide-tab-item";
        }
        changephoto();
    },false)
    nav5.addEventListener('mouseover',function(){
        photonum=4;
        for(var i=1;i<7;++i){
            document.getElementById("photo"+i.toString()).className="slide-item";
            document.getElementById("tab"+i.toString()).className="slide-tab-item";
        }
       changephoto();
    },false)
    nav6.addEventListener('mouseover',function(){
        photonum=5;
        for(var i=1;i<7;++i){
            document.getElementById("photo"+i.toString()).className="slide-item";
            document.getElementById("tab"+i.toString()).className="slide-tab-item";
        }
        changephoto();
    },false)
    var timer=setInterval(changephoto,5000);
}