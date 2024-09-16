var clicked = "";
var current_quantity = 0;

var token = localStorage.getItem("token");
var all_choose_ticket_detail = [localStorage.getItem("information")];
function showContent(content) {
  if (clicked !== content) {
    $(".content").empty();
    var html = `
    <div class="image-slider">
      <div class="image-slides">
        <img src="/image/rollimage1.png" alt="Slide 1" class="image-slide">
        <img src="/image/rollimage2.png" alt="Slide 2" class="image-slide">
        <img src="/image/rollimage3.png" alt="Slide 3" class="image-slide">
      </div>
      <div class="navigation-buttons">
        <button id="prev"><i class="fa-solid fa-chevron-left"></i></button>
        <button id="next"><i class="fa-solid fa-chevron-right"></i></button>
      </div>
    </div>
  `;
    $(".content").append(html);

    const slides = document.querySelector(".image-slides");
    const slideCount = document.querySelectorAll(".image-slide").length;
    let index = 0;

    function showSlide(n) {
      index = (n + slideCount) % slideCount;
      slides.style.transform = `translateX(${-index * 100}%)`;
    }

    document
      .getElementById("prev")
      .addEventListener("click", () => showSlide(index - 1));
    document
      .getElementById("next")
      .addEventListener("click", () => showSlide(index + 1));

    setInterval(() => showSlide(index + 1), 3000);

    var filmsLink = "http://localhost:8080/films";
    $.ajax({
      method: "GET",
      url: filmsLink + "/all_films",
      headers: {
        Authorization: "Bearer " + token,
      },
    }).done(function (mg) {
      if (mg.message === "done") {
        var heading = `<h1>Hello , What do you want to watch?</h1>`;
        $(".content").append(heading);
        $.each(mg.data, function (index, value) {
          var html = `
            <div class="movies">
              <div class="movie" style="position: relative;">
                <span class="rank">${value.age_req}+</span>
                <img class="image" src="${filmsLink}/files/${value.image}" alt="${value.filmName}">
                <h3>${value.filmName}</h3>
                <div class="movie-info">Genre: ${value.category_film}</div>
                <div class="movie-info">Running Time: ${value.running_time} Minutes</div>
                <div class="movie-info">Release date: ${value.release_date}</div>
                <div class="buttons">
                  <button class="button" onclick="buyTicket('${value.id}')" >Order</button>
                  <button class="button">
                    <i class="fa-solid fa-thumbs-up"></i>
                    ${value.likes} Like
                  </button>
                </div>
              </div>
            </div>
          `;
          $(".content").append(html);
        });
      }
    });
  }
  clicked = content;
}

function playinToday(Today) {
  // Xóa nội dung hiện tại trước khi thêm mới
}

function toggleSidebar() {
  const sidebar = document.getElementById("sidebar");
  const toggleButton = document.getElementById("toggleButton");
  sidebar.classList.toggle("hidden");

  toggleButton.textContent = sidebar.classList.contains("hidden") ? "⮞" : "⮜";
  content.style.marginLeft = sidebar.classList.contains("hidden")
    ? "20px"
    : "270px";
}

function fillFoods(content) {
  if (clicked != content) {
    $(".content").empty();
    var foodsLink = "http://localhost:8080/foods";
    $.ajax({
      method: "GET",
      url: foodsLink + "/getfoods",
      headers: {
        Authorization: "Bearer " + token,
      },
    }).done(function (mg) {
      if (mg.message === "done") {
        var heading = `<h1>All Foods</h1>`;
        $(".content").append(heading);
        $.each(mg.data, function (index, value) {
          var html = `
            <div class="foods">
              <div class="food" style="position: relative;">
                <img class="image" src="${foodsLink}/files/${
            value.foodImg
          }" alt="${value.foodName}">
                <div class="movie-info">Name : ${value.foodName}</div>
                <div class="movie-info">Price: ${
                  value.foodPrice * 1000
                } VND</div>
                <div class="button-foods">
                  <button class="button">Mua</button>
                </div>
              </div>
            </div>
          `;
          $(".content").append(html);
        });
      }
    });
    clicked = content;
  }
}

// showContent('dashboard');
let api;
function buyTicket(id) {
  let price = 0;
  var filmsLink = "http://localhost:8080/films";
  $.ajax({
    method: "POST",
    url: filmsLink + "/film?id=" + id,
    headers: {
      Authorization: "Bearer " + token,
    },
  }).done(function (mg) {
    if (mg.message === "done") {
      $("#modal").remove();
      price = mg.data.price;
      var html = `
    <div id="modal" class="modal">
    <div class="modal-content">
    <span id="close" class="close" style="position: absolute;">&times;</span>
            <h1>Đặt vé</h1>
            <div class="modal-movies">
              <div class="modal-movie" style="position: relative;">
                <span class="rank">${mg.data.age_req}+</span>
                <img class="image" src="${filmsLink}/files/${mg.data.image}" alt="${mg.data.filmName}">
                <h3>${mg.data.filmName}</h3>
                <div class="movie-info">Genre: ${mg.data.category_film}</div>
                <div class="movie-info">Running Time: ${mg.data.running_time} Minutes</div>
                <div class="movie-info">Release date: ${mg.data.release_date}</div>
              </div>
            </div>
        </div>
    </div>
      
            `;
      $(".content").append(html);
      $.ajax({
        method: "POST",
        url: filmsLink + "/getShowingList?id=" + id,
      }).done(function (mg) {
        const jsonString = JSON.stringify(mg.data);
        const jsonObj = JSON.parse(jsonString);
        api = jsonObj;

        if (mg.data.length > 0) {
          var html2 = `<div class="date-container">`;
          $.each(mg.data, function (index, value) {
            try {
              const { day, month, year } = extractDateParts(value.date);

              html2 += `
                  <div class="date-box selected" id = "date-box-selected" onclick="information('${
                    value.date
                  }' ,'${price}' , ${value.cinema.id} ,'${id}' )" >
                      <span class="date-label">${month} ${monthToCharacter(
                month
              )}</span>
                      <span class="date-number">${day}</span>
                  </div>
              `;
            } catch (error) {
              console.error("Error creating HTML:", error);
            }
          });
          html2 += "</div>";
          $(".modal-movies").append(html2);
        } else {
          var str = `
              <div class="date-container">
                <div class="date-box selected">
                    <span class="date-label">no movie schedule available</span>

                </div>
            `;
          str += "</div>";
          $(".modal-movies").append(str);
        }
      });

      document.getElementById("close").onclick = function () {
        document.getElementById("modal").style.display = "none";
      };

      window.onclick = function (event) {
        if (event.target == document.getElementById("modal")) {
          document.getElementById("modal").style.display = "none";
        }
      };
    }
  });
}

////onclick="selectTime('15:45 PM')
function information(date, price, id, id_films) {
  all_choose_ticket_detail = [localStorage.getItem("information")];
  all_choose_ticket_detail.push(id_films);
  all_choose_ticket_detail.push(id);
  console.log(all_choose_ticket_detail);
  const x = checkTimesForDate(api, date);

  $("#time-selection").remove();
  $(".quantity-choosen").remove();
  $(".submit-container").remove();
  if (x) {
    x.forEach((slot) => {
      const arr = [slot.cinema, slot.startTime, slot.endTime, slot.price];
      var time = arr[1] + "-" + arr[2];
      var str = `<div id="time-selection" onclick = "buttonSubmit('${time}')">
            <div class="time-slot" ">${time}</div>
        </div>
        `;

      $(".modal-movies").append(str);
      var str2 = `
        <div class = "quantity-choosen">
          
          <p>Ticket quantity : </p>
          <span id="quantity">0</span><button id="button-quantity" class="default" onclick="incrementQuantity(${price})">
            <span class="icon">+</span> 
          </button>
        </div>

        <div class="submit-container">
          <p id = "cost">Price : </p>
          <button class = "button-submit"type="submit" onclick="buy_Ticket()">Buy</button>
        </div>
      `;
      $(".modal-movies").append(str2);
    });
  } else {
    var str = `<div id="time-selection">
        <div class="time-slot" ">Chưa có lịch chiếu</div>
        </div>
        `;

    $(".modal-movies").append(str);
  }
}

function extractDateParts(dateString) {
  const [year, month, day] = dateString.split("-");
  return {
    day: parseInt(day, 10),
    month: parseInt(month, 10),
    year: parseInt(year, 10),
  };
}

function buttonSubmit() {}

function monthToCharacter(month) {
  const months = [
    "Jan",
    "Feb",
    "Mar",
    "Apr",
    "May",
    "Jun",
    "Jul",
    "Aug",
    "Sep",
    "Oct",
    "Nov",
    "Dec",
  ];

  return months[month - 1];
}

function checkTimesForDate(apiData, dateToCheck) {
  const times = apiData.filter((item) => item.date === dateToCheck);
  if (times.length > 0) {
    return times.map((item) => ({
      startTime: item.start,
      endTime: item.end,
      price: item.price,
      cinema: item.cinema.name,
    }));
  } else {
    return null;
  }
}

function incrementQuantity(price) {
  const quantityElement = document.getElementById("quantity");
  let currentQuantity = parseInt(quantityElement.textContent);
  currentQuantity += 1;
  quantityElement.textContent = currentQuantity;
  current_quantity = currentQuantity;

  const textPrice = document.getElementById("cost");
  textPrice.textContent = "Price : " + currentQuantity * price + " VND";
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

function buy_Ticket() {
  $.ajax({
    method: "POST",
    url: "http://localhost:8080/ticket/buy_ticket",
    data: {
      id_user: all_choose_ticket_detail[0],
      id_films: all_choose_ticket_detail[1],
      id_cinema: all_choose_ticket_detail[2],
      quantity: current_quantity,
    },
    headers: {
      Authorization: "Bearer " + token,
    },
  }).done(function (mg) {
    if (mg.message == "ok") {
      alert("Your had buy ticket , check it");
    }
  });
}
