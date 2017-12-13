$( document ).ready(function() {

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
                var companiesList = '';
                var len = result.length;
                for(var i=0; i<len; i++){
                    companiesList += '<option value="' + result[i].id + '">' + result[i].name + '</option>';
                }
                $('select#companyId').append(companiesList);
                console.log("Success: ", result);
            },
            error : function(e) {
                console.log("ERROR: ", e);
            }
        });
    }
});