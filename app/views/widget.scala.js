@(implicit request: RequestHeader)

var veritask = function() {

    loadDependency('@routes.Assets.versioned("lib/jsrender/jsrender.js").absoluteURL');

    var jQuery;
    if (window.jQuery === undefined || window.jQuery.fn.jquery !== '2.1.4') {
        loadDependency(
            '@routes.Assets.versioned("lib/jquery/jquery.js").absoluteURL',
            function () {
                jQuery = window.jQuery.noConflict(true);
                main();
            }
        );
    } else {
        jQuery = window.jQuery;
        main();
    }

    function loadDependency(src, callback) {
        var s = document.createElement('script');
        s.async = true;
        s.src = src;
        s.onload = callback;
        document.body.appendChild(s);
    }

    /******** Our main function ********/
    function main() {
        jQuery(document).ready(function ($) {
            jQuery('#example-widget-container').hide();
            jQuery.get('@routes.Application.widgetHTML.absoluteURL', function (data) {
                jQuery('#example-widget-container').append(data);
            });
        });
    }

    var task;

    function getTask() {
        //jQuery('#example-widget-container').show();
        // jQuery.getJSON('ATHIERHINroutes.Application.getTask.absoluteURL', function(data) {
        //     task = data;
        // });
    }

    function lazyGetTemplate(name) {
        // If the named remote template is not yet loaded and compiled
        // as a named template, fetch it. In either case, return a promise
        // (already resolved, if the template has already been loaded)
        var deferred = jQuery.Deferred();
        if (jQuery.templates[name]) {
            deferred.resolve();
        } else {
            jQuery.getScript(
                    "//www.jsviews.com/samples/resources/templates/"
                    + name + ".js")
                .then(function() {
                    if (jQuery.templates[name]) {
                        deferred.resolve();
                    } else {
                        alert("Script: \"" + name + ".js\" failed to load");
                        deferred.reject();
                    }
                });
        }
        return deferred.promise();
    }


    function challengeUser() {
        //get stuff
        //show stuff
        jQuery.getJSON('@routes.Tasksets.getTask.absoluteURL', function(data) {
            task = data;
            var myTmpl = window.jsrender.templates("<label>Name:</label> {{:subjectAttributes.attribute}} <img src='{{:subjectAttributes.attribute}}' alt='some_text'>");
            var html = myTmpl.render(task);
            jQuery('#example-widget-container').html(html);
            jQuery('#example-widget-container').show();
        });
    }

    function commitVerification() {

    }

    return {
        showTask: challengeUser
    };
}(); // We call our anonymous function immediately
