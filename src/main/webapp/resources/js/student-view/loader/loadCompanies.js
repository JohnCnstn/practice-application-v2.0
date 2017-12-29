$( document ).ready(function() {

    var companiesList = '';

    // GET REQUEST
    $("#getAllCompanies").click(function(event){
        event.preventDefault();
        ajaxGet();
    });

    // DO GET
    function ajaxGet(){
        $.ajax({
            type : "GET",
            url: window.location + "/getAllCompanies",
            success: function (result) {
                var len = result.length;

                if (len > 0) {
                    $(".submitHeadMaster").prop('disabled', false);
                } else {
                    callErrorAlert("You should create at least 1 company!");
                    $(".submitHeadMaster").prop('disabled', true);
                }
                if (companiesList.length == 0) {
                    for(var i=0; i<len; i++){
                        companiesList += '<option value="' + result[i].id + '">' + result[i].name + '</option>';
                    }
                    $('select#companyId').append(companiesList);
                    console.log("Success: ", result);
                }
            },
            error : function(e) {
                console.log("ERROR: ", e);
            }
        });
    }
});