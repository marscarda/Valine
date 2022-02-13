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

let startClick = () => { nextMetric(); }
let nextMetric = () => {
    if (position >= metrics.count) endReached();
    var metric = metrics.items[position];
    switch (metric.metrictype) {
        case METRICTYPE_PUBLICVIEW:
            metricPublicView(metric);
    }
    var fadein = new ElementFadeIn();
    fadein.setElement('metricpage');
    fadein.start();
};
let endReached = () => {
    alert ("End");
};