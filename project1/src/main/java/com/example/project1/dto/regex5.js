// form 이 submit 되면
// submit 중지
// id / password / email 유효성 검증 후 형식에 맞지 않는다면 메세지 추가 error에 표시

// form
// button type 확인 : submit(form submit), button(click)
document.querySelector("form").addEventListener("submit", (e) => {
  e.preventDefault();

  // 유효성 검사할 요소 찾기
  const name = document.querySelector("#name");
  const id = document.querySelector("#inputId");
  const email = document.querySelector("#staticEmail");
  const password = document.querySelector("#inputPassword");

  // 정규식 패턴 정의
  const regName = /^[가-힣]{2,5}$/;
  const regId = /^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[!@#])[A-Za-z0-9!@#]{6,12}$/;
  const regPwd =
    /^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[!@#_*])[A-Za-z0-9!@#_*]{8,12}$/;
  const regEmail =
    /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

  // closest(선택자) : 선택자와 일치한 가장 가까운 부모 요소
  console.log(id.closest("div"));

  if (!regName.test(name.value)) {
    name.closest("div").lastElementChild.innerHTML = "이름을 확인해 주세요";
  } else {
    name.closest("div").lastElementChild.innerHTML = "";
  }

  if (!regId.test(id.value)) {
    // regId 다음 다음 span 찾기
    // regId.nextElementSibling.nextElementSibling.innerHTML

    id.closest("div").lastElementChild.innerHTML = "아이디를 확인해 주세요";
  } else {
    id.closest("div").lastElementChild.innerHTML = "";
  }

  if (!regPwd.test(password.value)) {
    password.closest("div").lastElementChild.innerHTML =
      "비밀번호를 확인해 주세요";
  } else {
    password.closest("div").lastElementChild.innerHTML = "";
  }

  if (!regEmail.test(email.value)) {
    email.closest("div").lastElementChild.innerHTML = "이메일을 확인해 주세요";
  } else {
    email.closest("div").lastElementChild.innerHTML = "";
  }

  // 폼 유효성 검사 후 이상이 없으면
  //e.target.submit();
});
