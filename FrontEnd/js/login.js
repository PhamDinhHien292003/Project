const ds = document.getElementById("log");
ds.addEventListener("click", (event) => {
  event.preventDefault();
  checklogin();
});

function checklogin() {
  const link_login = "http://localhost:8080/Users";
  const username = document.getElementById("Username").value;
  const password = document.getElementById("password").value;
  const successMessage = document.getElementById("successMessage");
  const errorMessage = document.getElementById("errorMessage");
  successMessage.style.display = "none";
  errorMessage.style.display = "none";
  $.ajax({
    method: "POST",
    url: link_login + "/login",
    data: {
      username: username,
      password: password,
    },
  }).done(function (mg) {
      if (mg.data != null ) {
        successMessage.style.display = "block";
        setTimeout(() => {
          let str = mg.message; 
          localStorage.setItem("information",str.split(" ")[1]);
          localStorage.setItem("token" , mg.data)
          window.location.href = "/day2/vgcManagement.htm";
        }, 2000);
      } else {
        errorMessage.style.display = "block";
      }
      
  });
}
