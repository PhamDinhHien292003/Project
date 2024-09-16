var table_element = [];
var token = localStorage.getItem("token");
// Get all films
function loadFilms() {
  if(token != null ){
  const element = document.getElementById("main-statitics");
  if (element) {
    const displayStyle = window.getComputedStyle(element).display;
    if (displayStyle === "flex") {
      element.style.display = "none";
    }
  }
  document.getElementById("main-content").style.display = "flex";
  $(".app-content-films").empty();
  table_element = [];
  var filmsLink = "http://localhost:8080/films";
  $.ajax({
    method: "GET",
    url: filmsLink + "/all_films",
    headers: {
      Authorization: "Bearer " + token,
    }
  }).done(function (mg) {
    if (mg.message === "done") {
      $.each(mg.data, function (index, value) { 
        let html = `
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
            <img
              src="${filmsLink}/files/${value.image}"
              alt="product">
            <span>${value.filmName}</span>
          </div>
          <div class="product-cell category"><span class="cell-label">Category:</span>${
            value.category_film
          }</div>
         
          <div class="product-cell date"><span class="cell-label">date:</span> ${formatDate(
            value.release_date
          )}</div>
          <div class="product-cell running-time"><span class="cell-label">running-time:</span>${
            value.running_time
          }</div>
          <div class="product-cell likes"><span class="cell-label">likes:</span> ${
            value.likes
          }</div>
           <div class="product-cell status-cell">
            <span class="cell-label">Status:</span>
            <span class="status active"><button class="action-button" id="openModal" onclick = "film_action('${
              value.id
            }' ,
            '${value.filmName}','${value.category_film}' , '${formatDate(
          value.release_date
        )}'  )">Action</button> 
            
            
            </span>
          </div>
        </div>
        `;
        $("div.app-content-films").append(html);

        table_element.push(value);
      });
    }

    $("div.app-content-films").append('<div class ="contain-modal"></div>');
  });
}
else return ; 
}

function formatDate(dateString) {
  const date = new Date(dateString);
  const year = date.getFullYear();
  const month = String(date.getMonth() + 1).padStart(2, "0");
  const day = String(date.getDate()).padStart(2, "0");

  return `${year}-${month}-${day}`;
}
//// end of get films

document.querySelector(".jsFilter").addEventListener("click", function () {
  document.querySelector(".filter-menu").classList.toggle("active");
});

document.querySelector(".grid").addEventListener("click", function () {
  document.querySelector(".list").classList.remove("active");
  document.querySelector(".grid").classList.add("active");
  document.querySelector(".products-area-wrapper").classList.add("gridView");
  document
    .querySelector(".products-area-wrapper")
    .classList.remove("tableView");
});

document.querySelector(".list").addEventListener("click", function () {
  document.querySelector(".list").classList.add("active");
  document.querySelector(".grid").classList.remove("active");
  document.querySelector(".products-area-wrapper").classList.remove("gridView");
  document.querySelector(".products-area-wrapper").classList.add("tableView");
});

var modeSwitch = document.querySelector(".mode-switch");
modeSwitch.addEventListener("click", function () {
  document.documentElement.classList.toggle("light");
  modeSwitch.classList.toggle("active");
});

function filterTable() {
  const searchInput = document.querySelector(".search-bar");
  const searchValue = searchInput.value.toLowerCase();
  const filteredResults = [];

  for (let i = 0; i < table_element.length; i++) {
    if (table_element[i].filmName.toLowerCase().includes(searchValue)) {
      filteredResults.push(table_element[i]);
    }
  }
  $(".app-content-films").empty();
  var filmsLink = "http://localhost:8080/films";
  for (const obj of filteredResults) {
    let html = `
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
            <img
              src="${filmsLink}/files/${obj.image}"
              alt="product">
            <span>${obj.filmName}</span>
          </div>
          <div class="product-cell category"><span class="cell-label">Category:</span>${
            obj.category_film
          }</div>
         
          <div class="product-cell date"><span class="cell-label">date:</span> ${formatDate(
            obj.release_date
          )}</div>
          <div class="product-cell running-time"><span class="cell-label">running-time:</span>${
            obj.running_time
          }</div>
          <div class="product-cell likes"><span class="cell-label">likes:</span> ${
            obj.likes
          }</div>
           <div class="product-cell status-cell">
            <span class="cell-label">Status:</span>
            <span class="status active"><button class="action-button" id="openModal" onclick = "film_action('${
              obj.id
            }' ,
            '${obj.filmName}','${obj.category_film}' , '${formatDate(
      obj.release_date
    )}' , '${obj.image}' , '${obj.likes}' , '${obj.age_req}' , '${
      obj.running_time
    }'    )">Action</button> 
            </span>
          </div>
        </div>
        `;
    $("div.app-content-films").append(html);
  }
}

var infomation = [];
function film_action(
  id,
  name,
  cate,
  release_date,
  src_file,
  like,
  req,
  running_time
) {
  infomation = [
    id,
    name,
    cate,
    release_date,
    src_file,
    like,
    req,
    running_time,
  ];
  $(".contain-modal").load("/day2/modal_film.htm", function () {
    // Callback function to ensure the modal is loaded before trying to access it
    const modal = document.getElementById("modal-films");
    if (modal) {
      modal.style.display = "block";
      $("#title-detail").val(name);
      $("#category-detail").val(cate);
      $("#date-detail").val(release_date);
      $("#image-detail").val(src_file);
      document
        .getElementById("image-detail-choose")
        .addEventListener("change", function (e) {
          if (e.target.files[0]) {
            $("#image-detail").val(e.target.files[0].name);
          }
        });

      infomation[1] = document.getElementById("title-detail").value;
      infomation[2] = document.getElementById("category-detail").value;
      infomation[3] = document.getElementById("date-detail").value;
      infomation[7] = document.getElementById("image-detail").value;
    } else {
      console.error("Modal not found!");
    }
  });
}

function closeBtn(str) {
  const modal = document.getElementById(str);
  modal.style.display = "none";
}

function updateBtn() {
  var formData = new FormData();
  var fileInput = $("#image-detail-choose")[0].files[0];
  if (fileInput) {
    formData.append("id", infomation[0]);
    formData.append("name_film", document.getElementById("title-detail").value);
    formData.append(
      "release_date",
      document.getElementById("date-detail").value
    );
    formData.append(
      "category_film",
      document.getElementById("category-detail").value
    );
    formData.append("image_film", fileInput);
    $.ajax({
      method: "POST",
      url: "http://localhost:8080/films" + "/updateID",
      data: formData,
      headers: {
        Authorization: "Bearer " + token,
      },
      processData: false, // Important! Prevent jQuery from processing the data
      contentType: false, // Important! Set content type to false
    }).done(function (mg) {
      if (mg.data == true) {
        alert("change detail film successfully!");
        closeBtn("modal-films");
        loadFilms();
      }
    });
  } else {
    $.ajax({
      method: "POST",
      url: "http://localhost:8080/films" + "/updateID",
      data: {
        id: infomation[0],
        name_film: document.getElementById("title-detail").value,
        release_date: document.getElementById("date-detail").value,
        category_film: document.getElementById("category-detail").value,
      },
      headers: {
        Authorization: "Bearer " + token,
      },
    }).done(function (mg) {
      if (mg.data == true) {
        alert("change detail film successfully!");
        closeBtn("modal-films");
        loadFilms();
      }
    });
  }
}

function delBtn() {
  $.ajax({
    method: "POST",
    url: "http://localhost:8080/films" + "/del_from_id?id=" + infomation[0],
    headers: {
      Authorization: "Bearer " + token,
    },
  }).done(function (mg) {
    if ((mg.data = "successfully")) {
      if (confirm("U want delete that ?")) {
        closeBtn("modal-films");
        loadFilms();
      } else {
        return;
      }
    }
  });
}

function add_film() {
  $(".contain-modal").load("/day2/insert_film.html", function () {
    const val = document.getElementById("modal-films-insert");
    if (val) {
      val.style.display = "block";
      document
        .getElementById("insert-image-detail-choose")
        .addEventListener("change", function (e) {
          if (e.target.files[0]) {
            $("#insert-image-detail").val(e.target.files[0].name);
          }
        });
    }
  });
}

function okBtn(str) {
  if (
    document.getElementById("insert-title-detail").value != null &&
    document.getElementById("insert-date-detail").value &&
    document.getElementById("insert-runningtime-detail").value != null &&
    document.getElementById("insert-category-detail").value != null &&
    document.getElementById("insert-age-req-detail").value != null &&
    document.getElementById("insert-likes-detail").value != null &&
    $("#insert-image-detail-choose")[0].files[0] != null
  ) {
    var formData = new FormData();
    var fileInput = $("#insert-image-detail-choose")[0].files[0];
    if (fileInput) {
      formData.append(
        "name_film",
        document.getElementById("insert-title-detail").value
      );
      formData.append(
        "release_date",
        document.getElementById("insert-date-detail").value
      );
      formData.append(
        "running_time",
        document.getElementById("insert-runningtime-detail").value
      );

      formData.append(
        "category_film",
        document.getElementById("insert-category-detail").value
      );
      formData.append(
        "age_req",
        document.getElementById("insert-age-req-detail").value
      );
      formData.append(
        "likes",
        document.getElementById("insert-likes-detail").value
      );
      formData.append("image_film", fileInput);
      $.ajax({
        method: "POST",
        url: "http://localhost:8080/films" + "/insert",
        data: formData,
        contentType: false,
        processData: false,
        headers: {
          Authorization: "Bearer " + token,
        },
      }).done(function (mg) {
        if (mg.message == "done") {
          alert("DONE");
          closeBtn(str);
          loadFilms();
        } else {
          alert("ERROR ");
          closeBtn(str);
          loadFilms();
        }
      });
    } else {
      alert("select an image src");
    }
  } else {
    alert("You must enter complete infomation");
  }
}

function logout() {
  window.localStorage.clear();
  window.sessionStorage.clear();
  document.cookie.split(";").forEach(function (c) {
    document.cookie = c
      .replace(/^ +/, "")
      .replace(/=.*/, "=;expires=" + new Date().toUTCString() + ";path=/");
  });
  window.location.href = "/day2/login.html";
}
