$( document ).ready(function() {

    // GET REQUEST
    $("#getAllUniversities").click(function(event){
        event.preventDefault();
        ajaxGet();
    });

    // DO GET
    function ajaxGet(){
        $.ajax({
            type : "GET",
            url: window.location + "/getAllUniversities",
            success: function (result) {
                var html = '';
                var len = result.length;
                for(var i=0; i<len; i++){
                    html += '<option value="' + result[i].id + '">' + result[i].name + '</option>';
                }
                $('select#universityId').append(html);
                console.log("Success: ", result);
            },
            error : function(e) {
                console.log("ERROR: ", e);
            }
        });
    }
});