// function checkUsername(username) {
//     let flag = false;
//     $.getJSON("http://localhost:8080/ssm/ajaxExistsUsername", "username=" + username, function (data) {
//         if (data.ifExists) {
//             flag = true;
//         }
//     });
//     return flag;
// }
//
// // function checkCode(code) {
// //     let flag = false;
// //     $.getJSON("http://localhost:8080/ssm/ajaxCode", "code=" + code, function (data) {
// //         flag = data.code;
// //     });
// //     return flag;
// // }
//
// function changeCode() {
//     return "http://localhost:8080/ssm/kaptcha.jpg?date=" + new Date();  // 避免浏览器缓存，加一个随机参数
// }