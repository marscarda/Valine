class ToastMessage {
    element = null;
    curropacity = 0;
    setElement(document, elementid) { this.element = document.getElementById(elementid); }
    setColor(value) { this.element.style.color = value; }
    setText(value) { this.element.innerHTML = value; }
    start () { this.fadeIn(); }
    fadeIn () {
        this.curropacity += 0.2;
        if (this.curropacity > 1) {
            setTimeout(this.fadeOut.bind(this), 1200);
            return;
        }
        this.element.style.opacity = this.curropacity;
        setTimeout(this.fadeIn.bind(this), 50);
    }
    fadeOut () {
        this.curropacity -= 0.2;
        if (this.curropacity < 0) {
            this.element.style.opacity = 0;
            return;
        }
        this.element.style.opacity = this.curropacity;
        setTimeout(this.fadeOut.bind(this), 100);
    }
}
class ElementFadeIn {
    element = null;
    curropacity = 0;
    step = 0.2;
    setElement(elementid) { this.element = document.getElementById(elementid); }
    setStep(step) { this.step = step; }
    start () { this.fadeIn(); }
    fadeIn () {
        this.curropacity += this.step;
        if (this.curropacity > 1) {
            this.element.style.opacity = 1;
            return;
        }
        this.element.style.opacity = this.curropacity;
        setTimeout(this.fadeIn.bind(this), 50);
    }
}
class ElementOutUp {
    element = null;
    curmargin = 0;
    step = 100;
    setElement(elementid) { this.element = document.getElementById(elementid); }
    start () { 
        this.firstPause(); 
    }
    firstPause = () => { setTimeout(this.outUp.bind(this), 300); }
    outUp () {
        this.curmargin -= this.step;
        if (this.curmargin < -1000) {
            this.outFinish();
            return;
        }
        this.element.style.marginTop = this.curmargin + "px";
        setTimeout(this.outUp.bind(this), 30);
    }
    outFinish = () => {
        var empty = document.getElementById('formulation');
        while (empty.hasChildNodes())
            empty.removeChild(empty.lastChild);
        var empty = document.getElementById('options');
        while (empty.hasChildNodes())
            empty.removeChild(empty.lastChild);
        this.element.style.marginTop = 0;
        this.element.opacity = 0;
        setTimeout(this.onFinish.bind(this), 400);
    }
    onFinish = () => {};
}
