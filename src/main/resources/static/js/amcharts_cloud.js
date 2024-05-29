fetch("http://127.0.0.1:5000/tospring", {
  method: "POST",
  headers: {
    "Content-Type": "application/json",
  },
})
  .then((response) => response.json())
  .then((data) => {
    am4core.useTheme(am4themes_animated);
    var chart = am4core.create("chartdiv", am4plugins_wordCloud.WordCloud);
    chart.fontFamily = "Courier New";
    var series = chart.series.push(new am4plugins_wordCloud.WordCloudSeries());
    series.randomness = 0.1;
    series.rotationThreshold = 0.5;

    series.data = data.result;

    series.dataFields.word = "tag";
    series.dataFields.value = "count";

    series.heatRules.push({
      target: series.labels.template,
      property: "fill",
      min: am4core.color("#0000CC"),
      max: am4core.color("#CC00CC"),
      dataField: "value",
    });
    series.labels.template.url = "https://www.google.com/search?q={word}";
    series.labels.template.urlTarget = "_blank";

    series.labels.template.tooltipText = "{word}:\n[bold]{value}[/]";

    var subtitle = chart.titles.create();
    subtitle.text = "(click to open)";

    var title = chart.titles.create();
    title.text = "Most Popular Tags @ Finance";
    title.fontSize = 20;
    title.fontWeight = "800";
  });
