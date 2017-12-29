$( document ).ready(function() {

    var headMasterList = '';

    // GET REQUEST
    $("#getAllHeadMasters").click(function(event){
        event.preventDefault();
        ajaxGet();
    });

    // DO GET
    function ajaxGet(){
        $.ajax({
            type : "GET",
            url: window.location + "/getAllHeadMasters",
            success: function (result) {
                var len = result.length;

                if (len > 0) {
                    $(".submitPractice").prop('disabled', false);
                } else {
                    callErrorAlert("You should create at least 1 Head Master!");
                    $(".submitPractice").prop('disabled', true);
                }
                if (headMasterList.length == 0) {
                    for(var i=0; i<len; i++){
                        headMasterList += '<option value="' + result[i].id + '">' + result[i].userName + '</option>';
                    }
                    $('select#headMasterId').append(headMasterList);
                    console.log("Success: ", result);
                }
            },
            error : function(e) {
                console.log("ERROR: ", e);
            }
        });
    }
});