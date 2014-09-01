//<script type="text/javascript" charset="utf-8" src="assets/www/cordova.js"></script>


	var calllogPlugin = {
	    createEvent: function(successCallback,errorCallback) {
	
	    	
	
    cordova.exec(successCallback, // success callback function
            errorCallback, // error callback function
            'RecentList', // mapped to our native Java class called "CalendarPlugin"
            'getRecentList', // with this action name
            [{                  // and this array of custom arguments to create our entry
                
            }]);
    
	    }
};
