let initialize = () => {
    fillIntro();
    fillStart();
    var fadein = new ElementFadeIn();
    fadein.setElement('intropage');
    fadein.start();
};
let fillIntro = () => {
    var intro = document.getElementById('intro');
    intro.innerHTML = decodeURI(form.introsumary);
};
let fillStart = () => {
    var start = document.getElementById('start');
    start.innerHTML = decodeURI(form.startlabel);
};

let startClick = () => { 
    var page = document.getElementById('intropage');
    page.style.height = 0;
    page = document.getElementById('metricnav');
    page.style.height = 'auto';
    nextMetric(); 
}
let nextMetric = () => {
    if (position >= metrics.count) endReached();
    var metric = metrics.items[position];
    switch (metric.metrictype) {
        case METRICTYPE_PUBLICVIEW:
            metricPublicView(metric);
    }
    var fadein = new ElementFadeIn();
    fadein.setElement('metricnav');
    fadein.start();
};
let endReached = () => {
    alert ("End");
};