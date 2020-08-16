let socket = new WebSocket("ws://localhost:8080/bus");

socket.onopen = function(event) {
    alert("[open] Connection established");
};

socket.onmessage = function(event) {
    alert(`[message] Data received from server: ${event.data}`);
};

socket.onclose = function(event) {
    if (event.wasClean) {
        alert(`[close] Connection closed cleanly, code=${event.code} reason=${event.reason}`);
    } else {
        // サーバのプロセスが停止 ネットワークダウン
        alert('[close] Connection died');
    }
};

socket.onerror = function(event) {
    alert(`[error] ${event.message}`);
};