function callStatic() {
  const element = document.getElementById("main-statitics");
  if (!element) {
    $.get("/day2/main-statitics.html", function (data) {
      const parsedData = $.parseHTML(data);
        $(".app-container").append(parsedData);
      const newElement = document.getElementById("main-statitics");
      if (newElement) {
        document.getElementById("main-content").style.display = "none";
        newElement.style.display = "flex";
      } else {
        console.error("ERROR");
      }
    }).fail(function (xhr, status, error) {
      alert("Err: " + xhr.status + " " + xhr.statusText);
    });
  } else {
    document.getElementById("main-content").style.display = "none";
    element.style.display = "flex";
    return ;
  }
  show_content(); 
}



function show_content(){
  let token = localStorage.getItem("token")
  $.ajax({
      method : "GET" , 
      url : "http://localhost:8080/ticket/get" , 
      headers : {
        'Authorization' : 'Bearer ' + token 
      }
  }).done(function(mg){
    $('.app-content-statitics').empty();
    $.each(mg.data , function (index, value) {
      var html  = `
        <div class="products-row" >
          <button class="cell-more-button">
            <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24" fill="none"
              stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
              class="feather feather-more-vertical">
              <circle cx="12" cy="12" r="1" />
              <circle cx="12" cy="5" r="1" />
              <circle cx="12" cy="19" r="1" /></svg>
          </button>
          <div class="product-cell image">
            <span>${value.filmsDTO.filmName}</span>
          </div>
          <div class="product-cell category"><span class="cell-label">Category:</span>${
            value.cinemaDTO.name
          }</div>
         
          <div class="product-cell date"><span class="cell-label">date:</span> ${
            value.userDTO.name
          }</div>
          <div class="product-cell running-time"><span class="cell-label">running-time:</span>${
            value.quantity 
          }</div>
          <div class="product-cell running-time"><span class="cell-label">running-time:</span>${
            value.filmsDTO.price * value.quantity
          } VND</div>

           
          </div>
        </div>
      ` 
      $('.app-content-statitics').append(html);
    })
      

  })
}