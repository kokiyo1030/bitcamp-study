document.addEventListener("DOMContentLoaded", () => {
    // DOM Tree를 완성 한 후, 렌더링 전에 호출됨
    loadHeader();
    loadFooter();

    getUserInfo();
});

function loadHeader() {    // 페이지 헤더 로딩
    let xhr = new XMLHttpRequest();
    xhr.open("GET", "/header.html", false);
    xhr.send();
    let parser = new DOMParser();
    let doc = parser.parseFromString(xhr.responseText, "text/html");

    // console.log(doc.querySelector("#page-header"));
    document.body.insertBefore(doc.querySelector("#page-header"), document.body.firstChild);
}

function loadFooter() {    // 페이지 푸터 로딩
    let xhr = new XMLHttpRequest();
    xhr.open("GET", "/footer.html", false);
    xhr.send();
    let parser = new DOMParser();
    let doc = parser.parseFromString(xhr.responseText, "text/html");

    // console.log(doc.querySelector("#page-footer"));
    document.body.appendChild(doc.querySelector("#page-footer"));
}

function getUserInfo() {    // 페이지 푸터 로딩
    let xhr = new XMLHttpRequest();
    xhr.open("GET", "/auth/user-info", false);
    xhr.send();

    let result = JSON.parse(xhr.responseText);
    if (result.status === "success") {
        document.querySelector(".login-div").style["display"] = "none";
        document.querySelector(".user-div #user-name").innerHTML = result.data.name;
    } else {
        document.querySelector(".user-div").style["display"] = "none";
    }
}
