////////////////////////////////////////////////////////////////////////////////////////////////
/* 메인페이지 리스트 목록 숨기기, 보이기 */
////////////////////////////////////////////////////////////////////////////////////////////////
function genreClick() {
	$("#genre").show();
	$("#situation").hide();
	$("#mood").hide();
	$("#a").hide();
	$("#b").hide();
}

function situationClick() {
	$("#genre").hide();
	$("#situation").show();
	$("#mood").hide();
	$("#a").hide();
	$("#b").hide();
}

function moodClick() {
	$("#genre").hide();
	$("#situation").hide();
	$("#mood").show();
	$("#a").hide();
	$("#b").hide();
}

function aClick() {
	$("#genre").hide();
	$("#situation").hide();
	$("#mood").hide();
	$("#a").show();
	$("#b").hide();
}

function bClick() {
	$("#genre").hide();
	$("#situation").hide();
	$("#mood").hide();
	$("#a").hide();
	$("#b").show();
}

////////////////////////////////////////////////////////////////////////////////////////////////
/* 공지 */
////////////////////////////////////////////////////////////////////////////////////////////////
/*
$('#btn-modal').click(function(e){
	e.preventDefault();
	$('#notificationModal').modal("show");
});
*/

////////////////////////////////////////////////////////////////////////////////////////////////
/* 사이드 바 언어 버튼 숨기기 보이기 */
////////////////////////////////////////////////////////////////////////////////////////////////

function langHide() {
	const lang = document.getElementById('language');

	if(lang.style.display != 'none') {
	    lang.style.display = 'none';
	  }
	  else {
	    lang.style.display = 'block';
	  }
}

////////////////////////////////////////////////////////////////////////////////////////////////
/* 사이드 바 */
////////////////////////////////////////////////////////////////////////////////////////////////

/* EXPANDER MENU */
const showMenu = (toggleId, navbarId, bodyId) => {
    const toggle = document.getElementById(toggleId),
    navbar = document.getElementById(navbarId),
    bodypadding = document.getElementById(bodyId)

    if( toggle && navbar ) {
        toggle.addEventListener('click', ()=>{
            navbar.classList.toggle('expander');

            bodypadding.classList.toggle('body-pd')
        })
    }
}

showMenu('nav-toggle', 'navbar', 'body-pd')

/* LINK ACTIVE */
const linkColor = document.querySelectorAll('.nav__link')
function colorLink() {
    linkColor.forEach(l=> l.classList.remove('active'))
    this.classList.add('active')
}
linkColor.forEach(l=> l.addEventListener('click', colorLink))

/* COLLAPSE MENU */
const linkCollapse = document.getElementsByClassName('collapse__link')
var i

for(i=0;i<linkCollapse.length;i++) {
    linkCollapse[i].addEventListener('click', function(){
        const collapseMenu = this.nextElementSibling
        collapseMenu.classList.toggle('showCollapse')

        const rotate = collapseMenu.previousElementSibling
        rotate.classList.toggle('rotate')
    });
}

////////////////////////////////////////////////////////////////////////////////////////////////
/* register.jsp */
////////////////////////////////////////////////////////////////////////////////////////////////

function checkID() {
	var id = register.id.value;

    if (id  == "")
    {
		alert("아이디를 입력해 주세요."); 
		register.id.focus(); 
		return; 
    }

	window.open("checkId.jsp?id="+id,"win", "width=255, height=145, scrollbars=no, resizable=no");
}

function checkValue() {
	if (document.register.name.value == "")
	{
		alert("이름을 입력해 주세요.");
		document.register.name.focus();
		return;
	}

	if (document.register.id.value == "")
	{
		alert("아이디를 입력해주세요.");
		document.register.id.focus();
		return;
	}

	if (document.register.password.value == "")
	{
		alert("비밀번호를 확인해 주세요.");
		document.register.password.focus();
		return;
	}

	if (document.register.password.value != document.register.passwordCheck.value)
	{
		alert("비밀번호를 확인해 주세요.");
		document.register.password.focus();
		return;
	}

	if (document.querySelector('input[name="personalInfo"]').checked == false)
	{
		alert("개인정보 처리방침을 동의해야 합니다.");
		return;
	}

	document.register.submit();
}

////////////////////////////////////////////////////////////////////////////////////////////////
/* login.jsp */
////////////////////////////////////////////////////////////////////////////////////////////////

function checkIdValue() {
	if (document.login.id.value == "")
	{
		alert("아이디를 입력해 주세요.");
		document.login.id.focus();
		return;
	}
	if (document.login.password.value == "")
	{
		alert("비밀번호를 입력해 주세요.");
		document.login.password.focus();
		return;
	}

	document.login.submit();
}

////////////////////////////////////////////////////////////////////////////////////////////////
/* choiceCategory.jsp */
////////////////////////////////////////////////////////////////////////////////////////////////

const gd = document.getElementById('genreDiv');
const sd = document.getElementById('situationDiv');
const md = document.getElementById('moodDiv');

function genreShow() {
	gd.style.display = 'block';
	sd.style.display = 'none';
	md.style.display = 'none';
}

function situationShow() {
	gd.style.display = 'none';
	sd.style.display = 'block';
	md.style.display = 'none';
}

function moodShow() {
	gd.style.display = 'none';
	sd.style.display = 'none';
	md.style.display = 'block';
}
