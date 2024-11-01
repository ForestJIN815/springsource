document.querySelector("tbody").addEventListener("click", (e) => {
  // 완료를 클릭하면  체크박스 value 가져오기
  const chk = e.target;

  console.log(chk.value);
  console.log(chk.checked);

  // comForm 에 id value 값을 가져온 id 로 변경
  const comForm = document.querySelector("#comForm");
  comForm.querySelector("[name='id']").value = chk.value;
  comForm.querySelector("[name='completed']").value = chk.checked;
  //
  console.log(comForm.innerHTML);
  comForm.submit();
});
