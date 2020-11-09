$(".imagem").change(function() {
	readURL(this);
});
		
function readURL(input) {
	if (input.files && input.files[0]) {
		var reader = new FileReader();
			    
		reader.onload = function(e) {
		$('.image-thumbnail').attr('src', e.target.result);
		}
		$('#thumbnail').css('height','auto');
			    
		reader.readAsDataURL(input.files[0]); // convert to base64 string
	}
}