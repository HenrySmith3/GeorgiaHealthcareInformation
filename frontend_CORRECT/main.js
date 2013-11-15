$(function() {
  //Show/hide elements to set up the form
  var $form = $("form.form-stepped");
  var $step_list = $("ul.pagination", $form).show().children("li");
  $steps = $(".form-step", $form);
  $steps.hide().eq(0).show();
  
  $buttons = $(".step-control>button", $form);
  $buttons.hide().eq(2).show();
  
  var step = 0;
  $buttons.eq(0).click(function (e) {
    e.preventDefault();
    $steps.eq(step).hide();
    $step_list.eq(step).toggleClass("active disabled");
    step--;
    $steps.eq(step).show();
    $step_list.eq(step).toggleClass("active");
    if (step === 0) $(this).hide();
    else if (step === 2) {
      $buttons.eq(1).hide();
      $buttons.eq(2).show();
    }
  });
  $buttons.eq(2).click(function (e) {
    e.preventDefault();
    $steps.eq(step).hide();
    $step_list.eq(step).toggleClass("active");
    step++;
    $steps.eq(step).show();
    $step_list.eq(step).toggleClass("active disabled");
    if (step === 3) {
      $(this).hide();
      $buttons.eq(1).show();
    } else if (step === 1) {
      $buttons.eq(0).show();
    }
  });

});
