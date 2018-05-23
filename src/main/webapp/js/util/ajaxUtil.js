function ajaxPost(actionUrl, params, success, fail) {
	$.ajax({
		type : "POST",
		url : actionUrl,
		data : "{" + "\"data\":" + convertObjectToJSON(params) + "}",
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(response) {
			success(response);
		},
		failure : function(msg) {
		},
		always : function(msg) {
		},
		error : function(xhr, error, msg) {
		}

	});
}

function ajaxGet(actionUrl, data, success, fail) {
	$.ajax({
		type : "GET",
		url : actionUrl,
		data : convertObjectToJSON(data),
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(response) {
			success(response);
		},
		failure : function(msg) {
			fail(msg);
		},
		always : function(msg) {
			fail(msg);
		},
		error : function(xhr, error, msg) {
		}

	});
}

function convertObjectToJSON(obj) {
	if (!obj)
		return "{}";
	var newObj = new Object();
	var allProperties = Object.keys(obj);
	for (var iProperty = 0; iProperty < allProperties.length; iProperty++) {
		var key = allProperties[iProperty];
		newObj[key] = obj[key];
	}
	return JSON.stringify(newObj);
}
