$(document).ready(function() {
    var assignTable =  $('#setOnPracticeTable').DataTable({
        "paging": false,
        "lengthChange": false,
        "searching": false,
        "ordering": false,
        "info": false,
        "autoWidth": false,
        "colReorder": true
    });

    var assignPracticeList = [];

    $('#setOnPracticeTable tbody').on( 'click', 'tr', function () {
        $(this).toggleClass('selected');
    } );

    $("#studentOnPracticeForm").submit(function(event) {

        var elements = document.getElementsByClassName('selected');

        for (var i = 0; i < elements.length; i++) {
            var element = elements[i];
            assignPracticeList.push(element.id);
        }

        $(".selected").removeClass("selected");

        event.preventDefault();
        setOnPractice(assignPracticeList);
    });

    function setOnPractice(assignPracticeList){

        // PREPARE FORM DATA

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: window.location + "/assignOnPractice",
            data: JSON.stringify(assignPracticeList),
            dataType: 'json',
            success: function (result) {
                $('#studentOnPracticeModal').modal('hide');
                callSuccessAlert('You assigned students!');
                console.log(result);
            },
            error: function (e) {
                $('#studentOnPracticesModal').modal('hide');
                callErrorAlert(e.responseText);
                console.log("ERROR: ", e);
            }
        });

        resetData();
    }

    function resetData(){
        $(".action-button").prop('disabled', true);
        assignPracticeList = [];
    }
} );