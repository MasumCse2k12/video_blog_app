

function showGlobalMessage(elem, timeout, messageType){
    M.toast({html: elem,displayLength:timeout,classes:messageType});
}

function showGlobalLoader(state){
    if(state){
        $('#global-loader-wrapper').show();
    }else{
        $('#global-loader-wrapper').hide();
    }
}


$(document).ready(function () {
    // document.onmousemove = handleMouseMove;

    function handleMouseMove(event) {
        var eventDoc, doc, body;

        event = event || window.event; // IE-ism

        // If pageX/Y aren't available and clientX/Y are,
        // calculate pageX/Y - logic taken from jQuery.
        // (This is to support old IE)
        if (event.pageX == null && event.clientX != null) {
            eventDoc = (event.target && event.target.ownerDocument) || document;
            doc = eventDoc.documentElement;
            body = eventDoc.body;

            event.pageX = event.clientX +
                (doc && doc.scrollLeft || body && body.scrollLeft || 0) -
                (doc && doc.clientLeft || body && body.clientLeft || 0);
            event.pageY = event.clientY +
                (doc && doc.scrollTop || body && body.scrollTop || 0) -
                (doc && doc.clientTop || body && body.clientTop || 0);
        }

        // Use event.pageX / event.pageY here
        // Add a dot to follow the cursor
        // dot = document.createElement('div');
        // dot.className = "dot";
        // dot.style.left = event.pageX + "px";
        // dot.style.top = event.pageY + "px";
        // document.body.appendChild(dot);

        // console.log('X Position: ', event.pageX, 'Y Position: ', event.pageY);

        // console.log('Inner Height: ', window.innerHeight);

        if ((window.innerHeight - event.pageY) < 200) {
            console.log('200 from bottom');
        }
    }

});
// })();