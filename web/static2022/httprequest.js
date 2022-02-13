class HttpRequest {
    url = null;
    callback = null;
    formdata = null;
    data = '';
    setURL (u) { this.url = u; }
    setCallBack (call) { this.callback = call; } 
    addParam (name, value) {
        if (this.data.length !== 0) this.data += '&';
        this.data += name + '=' + value;
    }
    setFormData (fdata) { this.formdata = fdata; }
    executepost () {
        var request = new XMLHttpRequest();
	request.timeout = 10000;
	request.onreadystatechange = () => {
            if (request.readyState !== 4) return;
            var objresp = {};
            try { objresp = eval ('(' + request.responseText + ')'); } 
            catch (err) {}
            this.callback(request.status, objresp);
	}
	request.open('POST',this.url,true);
        if (this.formdata === null) {
            request.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        }
        if (this.formdata !== null) 
            request.send(this.formdata);
        else 
            request.send(this.data);
    }
}
class Cookies {
    static setNew = (name, value) => { document.cookie = name + '=' + value; }
    static setNewPersistent = (name, value, days) => {
        const d = new Date();
        d.setTime(d.getTime() + (days*24*60*60*1000));
        let expires = "expires="+ d.toUTCString();
        document.cookie = name + '=' + value + ";" + expires + ";path=/";
    }
    static get = (cname) => {
        let name = cname + "=";
        let decodedCookie = decodeURIComponent(document.cookie);
        let ca = decodedCookie.split(';');
        for(let i = 0; i <ca.length; i++) {
          let c = ca[i];
          while (c.charAt(0) === ' ') {
            c = c.substring(1);
          }
          if (c.indexOf(name) === 0) {
            return c.substring(name.length, c.length);
          }
        }
        return "";
    }
}
