am5.ready(function () {
  // Create root element
  // https://www.amcharts.com/docs/v5/getting-started/#Root_element
  var root = am5.Root.new("chartnetwork");

  // Set themes
  // https://www.amcharts.com/docs/v5/concepts/themes/
  root.setThemes([am5themes_Animated.new(root)]);

  var data = {
    name: "Root",
    value: 50,
    children: [
      {
        name: "1",
        linkWith: ["2"],
        children: [
          {
            name: "A",
            linkWith: ["3", "F"],
            children: [
              { name: "A1", value: 1 },
              { name: "A2", value: 1 },
              { name: "A3", value: 1 },
              { name: "A4", value: 1 },
              { name: "A5", value: 1 },
            ],
          },
          {
            name: "B",
            children: [
              { name: "B1", value: 1 },
              { name: "B2", value: 1 },
              { name: "B3", value: 1 },
              { name: "B4", value: 1 },
              { name: "B5", value: 1 },
            ],
          },
          {
            name: "C",
            children: [
              { name: "C1", value: 1 },
              { name: "C2", value: 1 },
              { name: "C3", value: 9 },
            ],
          },
        ],
      },

      {
        name: "2",
        linkWith: ["3"],
        children: [
          {
            name: "D",
            value: 1,
          },
          {
            name: "E",
            value: 1,
          },
        ],
      },
      {
        name: "3",
        children: [
          {
            name: "F",
            children: [
              { name: "F1", value: 1 },
              { name: "F2", value: 2 },
              { name: "F3", value: 1 },
            ],
          },
          {
            name: "H",
            children: [
              { name: "H1", value: 1 },
              { name: "H2", value: 4 },
              { name: "H3", value: 1 },
              { name: "H4", value: 1 },
            ],
          },
          {
            name: "G",
            linkWith: ["2"],
            children: [
              { name: "G1", value: 1 },
              { name: "G2", value: 1 },
              { name: "G3", value: 5 },
            ],
          },
        ],
      },
    ],
  };

  // Create wrapper container
  var container = root.container.children.push(
    am5.Container.new(root, {
      width: am5.percent(100),
      height: am5.percent(100),
      layout: root.verticalLayout,
    })
  );

  // Create series
  // https://www.amcharts.com/docs/v5/charts/hierarchy/#Adding
  var series = container.children.push(
    am5hierarchy.ForceDirected.new(root, {
      singleBranchOnly: false,
      downDepth: 1,
      topDepth: 0,
      maxRadius: 25,
      minRadius: 12,
      valueField: "value",
      categoryField: "name",
      childDataField: "children",
      idField: "name",
      linkWithStrength: 0.3,
      linkWithField: "linkWith",
      manyBodyStrength: -15,
      centerStrength: 0.5,
    })
  );

  series.get("colors").set("step", 2);

  series.data.setAll([data]);
  series.set("selectedDataItem", series.dataItems[0]);

  // Make stuff animate on load
  series.appear(1000, 100);
}); // end am5.ready()