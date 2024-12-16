//삭제 클릭시 actionForm 전송
//정말로 삭제하시겠습니까?
const actionForm = document.querySelector("#actionForm");

document.querySelector(".btn-danger").addEventListener("click", (e) => {
  if (confirm("정말로 삭제하시겠습니까?")) {
    actionForm.action = "/book/remove";
    actionForm.submit();
  }
});

document.querySelector(".btn-secondary").addEventListener("click", (e) => {
  // 목록 클릭 시 a 태그 기능 중지
  e.preventDefault();

  // actionForm 에서 id 요소 제거하기
  actionForm.querySelector("[name='id']").remove();
  // actionForm method는 get 변경하기
  actionForm.method = "get";
  // actionForm action은 list 변경하기
  actionForm.action = "/book/list";

  // actionForm submit()
  actionForm.submit();
});
