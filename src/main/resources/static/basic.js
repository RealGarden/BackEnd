function regist() {//id.pw,name,age,phone,email
    let csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
    let csrfToken = $("meta[name='_csrf']").attr("content");
    let csrfHeader = $("meta[name='_csrf_header']").attr("content");
    let headers = {};
    headers[csrfHeader] = csrfToken;

    let userId = $('#id').val();
    let userPw = $('#pw').val();
    let userName = $('#name').val();
    let userAge = $('#age').val();
    let userPhone = $('#phone').val();
    let userEmail = $('#email').val();
    let userProfile=$('#profile').val();
    let data = {
        'profile':userProfile,
        'id': userId,
        'pw': userPw,
        'name': userName,
        'age': userAge,
        'phone': userPhone,
        'email': userEmail
    };

    $.ajax({
        type: 'POST',
        url: '/api/members',
        contentType: "application/json", // JSON 형식으로 전달함을 알리기
        headers: headers,
        data: JSON.stringify(data),
        success: function () {
            alert('메시지가 성공적으로 작성되었습니다.');
        }
    });
}

