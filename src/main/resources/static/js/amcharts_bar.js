am5.ready(function () {
  // Create root element
  // https://www.amcharts.com/docs/v5/getting-started/#Root_element
  var root = am5.Root.new("chartbar");

  // Set themes
  // https://www.amcharts.com/docs/v5/concepts/themes/
  root.setThemes([am5themes_Animated.new(root)]);

  // Create chart
  // https://www.amcharts.com/docs/v5/charts/xy-chart/
  var chart = root.container.children.push(
    am5xy.XYChart.new(root, {
      panX: false,
      panY: false,
      paddingLeft: 0,
      wheelX: "panX",
      wheelY: "zoomX",
      layout: root.verticalLayout,
    })
  );

  // Add legend
  // https://www.amcharts.com/docs/v5/charts/xy-chart/legend-xy-series/
  var legend = chart.children.push(
    am5.Legend.new(root, {
      centerX: am5.p50,
      x: am5.p50,
    })
  );

  var data = [
    {
      dailydate: "2024-05-15",
      stock: 84,
      price: 76,
      may: 49,
      revenue: 41,
      could: 36,
      positive: 34,
      growth: 34,
      potential: 34,
      due: 32,
      increased: 30,
    },
    {
      dailydate: "2024-05-17",
      stock: 90,
      price: 80,
      may: 38,
      revenue: 29,
      could: 43,
      positive: 31,
      growth: 27,
      potential: 30,
      due: 34,
      increased: 28,
    },
    {
      dailydate: "2024-05-20",
      stock: 95,
      price: 92,
      may: 48,
      revenue: 34,
      could: 45,
      positive: 34,
      growth: 33,
      potential: 39,
      due: 31,
      increased: 32,
    },
  ];

  // Create axes
  // https://www.amcharts.com/docs/v5/charts/xy-chart/axes/
  var xRenderer = am5xy.AxisRendererX.new(root, {
    cellStartLocation: 0.1,
    cellEndLocation: 0.9,
    minorGridEnabled: true,
  });

  var xAxis = chart.xAxes.push(
    am5xy.CategoryAxis.new(root, {
      categoryField: "dailydate",
      renderer: xRenderer,
      tooltip: am5.Tooltip.new(root, {}),
    })
  );

  xRenderer.grid.template.setAll({
    location: 1,
  });

  xAxis.data.setAll(data);

  var yAxis = chart.yAxes.push(
    am5xy.ValueAxis.new(root, {
      renderer: am5xy.AxisRendererY.new(root, {
        strokeOpacity: 0.1,
      }),
    })
  );

  // Add series
  // https://www.amcharts.com/docs/v5/charts/xy-chart/series/
  function makeSeries(name, fieldName) {
    var series = chart.series.push(
      am5xy.ColumnSeries.new(root, {
        name: name,
        xAxis: xAxis,
        yAxis: yAxis,
        valueYField: fieldName,
        categoryXField: "dailydate",
      })
    );

    series.columns.template.setAll({
      tooltipText: "{name}, {categoryX}:{valueY}",
      width: am5.percent(90),
      tooltipY: 0,
      strokeOpacity: 0,
    });

    series.data.setAll(data);

    // Make stuff animate on load
    // https://www.amcharts.com/docs/v5/concepts/animations/
    series.appear();

    series.bullets.push(function () {
      return am5.Bullet.new(root, {
        locationY: 0,
        sprite: am5.Label.new(root, {
          text: "{valueY}",
          fill: root.interfaceColors.get("alternativeText"),
          centerY: 0,
          centerX: am5.p50,
          populateText: true,
        }),
      });
    });

    legend.data.push(series);
  }

  makeSeries("stock", "stock");
  makeSeries("price", "price");
  makeSeries("may", "may");
  makeSeries("revenue", "revenue");
  makeSeries("could", "could");
  makeSeries("positive", "positive");
  makeSeries("growth", "growth");
  makeSeries("potential", "potential");
  makeSeries("due", "due");
  makeSeries("increased", "increased");

  // Make stuff animate on load
  // https://www.amcharts.com/docs/v5/concepts/animations/
  chart.appear(1000, 100);
}); // end am5.ready()