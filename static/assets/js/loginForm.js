$(function(){
var textfield = $("input[name=username]");
var passwordField = $("input[placeholder=password]");
            $('button[type="submit"]').click(function(e) {
                e.preventDefault();
                //little validation just to check username
                if (textfield.val() === ""){
                    //remove success mesage replaced with error message
					console.log('username required');
                    $("#output").removeClass(' alert alert-success');
                    $("#output").addClass("alert alert-danger animated fadeInUp").html("please enter a username ");
                }
				else if(passwordField.val()===""){
					console.log('pass required');
					$("#output").removeClass(' alert alert-success');
                    $("#output").addClass("alert alert-danger animated fadeInUp").html("please enter a password ");
				}
				else{
					$('#loginForm').submit();
                }
                //console.log(textfield.val());

            });
});
