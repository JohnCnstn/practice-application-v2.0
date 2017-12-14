$(document).ready(function() {
    var table =  $('#example1').DataTable({
        "paging": true,
        "lengthChange": false,
        "searching": true,
        "ordering": true,
        "info": false,
        "autoWidth": false,
        "colReorder": true
    });

    $('#example1 tbody').on( 'click', 'tr', function () {
        $(this).toggleClass('selected');
    } );

} );