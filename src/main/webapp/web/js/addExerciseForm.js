$(document).ready(function () {
    $('#add').click(function(){
        var $div = $('div[class^="exercise-input"]:last');
        var num = parseInt( $div.prop("id").match(/\d+/g), 10 ) + 1;
        var newExercise = $div.clone().prop("id", "exercise[" + num + "]").find("input").val("").end();
        newExercise.find("input[name*='exerciseId']").val(0);
        newExercise.find("input").each(function () {
            var inputName = $(this).attr('name').match(/[a-zA-Z]+/g);
            $(this).attr('name', inputName + "[" + num + "]");
        })
        $('#exercises').append(newExercise);
    });
    $('#delete').click(function(){
        var $allExerciseDivs = document.getElementsByClassName("exercise-input");
        if($allExerciseDivs.length > 1) {
            var $div = $('div[class^="exercise-input"]:last');
            $div.remove();
        }
    });
});