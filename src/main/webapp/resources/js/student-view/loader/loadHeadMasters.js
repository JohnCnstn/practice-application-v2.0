$( document ).ready(function() {

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
                var headMasterList = '';
                var len = result.length;
                for(var i=0; i<len; i++){
                    headMasterList += '<option value="' + result[i].id + '">' + result[i].userName + '</option>';
                }
                $('select#headMasterId').append(headMasterList);
                console.log("Success: ", result);
            },
            error : function(e) {
                console.log("ERROR: ", e);
            }
        });
    }
});