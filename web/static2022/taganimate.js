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
