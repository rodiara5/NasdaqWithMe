// Highcharts.getJSON('https://api.coingecko.com/api/v3/coins/ethereum/ohlc?vs_currency=usd&days=365', function (heikin) {

const ticker = document.getElementById('ticker').textContent;
fetch(`/api/v1/nasdaq/bollinger?ticker=${ticker}`)
    .then(response => response.json())
    .then(data => {
        // 데이터 형식에 맞게 변환
        const processedData = data.map(item => [
            item.date, // timestamp
            item.openPrice,
            item.highPrice,
            item.lowPrice,
            item.closePrice
        ]);
        Highcharts.stockChart('bollinger', {
            chart: {
                backgroundColor: "#FFFFFF",
                borderRadius: 15,
                height: 700,
            },

            title: {
                text: `${ticker}의 주가차트`,
                style: {
                color: "#fff",
                },
            },

            series: [   {
                    type: "heikinashi",
                    name: `${ticker}`,
                    id: `${ticker}`,
                    data: processedData,
                },
                {
                    type: "bb",
                    name: "Bollinger Bands",
                    id: "overlay",
                    linkedTo: `${ticker}`,
                    yAxis: 0,
                },],

            rangeSelector: {
                selected:0,
                buttons: [
                {
                    type: "month",
                    count: 1,
                    text: "1m",
                    title: "View 1 month",
                },
                {
                    type: "month",
                    count: 4,
                    text: "4m",
                    title: "View 4 months",
                },
                {
                    type: "month",
                    count: 8,
                    text: "8m",
                    title: "View 8 months",
                },
                {
                    type: "ytd",
                    text: "YTD",
                    title: "View year to date",
                },
                {
                    type: "all",
                    count: 1,
                    text: "All",
                    title: "View All",
                },
                ],
                allButtonsEnabled: false,
                buttonTheme: {
                // styles for the buttons
                fill: "none",
                stroke: "none",
                "stroke-width": 0,
                r: 8,
                style: {
                    color: "#4F6C89",
                    fontWeight: "bold",
                },
                states: {
                    hover: {},
                    select: {
                    fill: "transparent",
                    style: {
                        color: "#D76F2A",
                    },
                    },
                },
                },
                inputBoxBorderColor: "#4F6C89",
                inputBoxWidth: 110,
                inputBoxHeight: 18,
                inputStyle: {
                color: "#4F6C89",
                fontWeight: "bold",
                },
                labelStyle: {
                color: "#cbd1d6",
                fontWeight: "bold",
                },
            },

            plotOptions: {
                line: {
                dashStyle: "dash",
                },
                series: {
                borderColor: "red",
                marker: {
                    enabled: false,
                    radius: 0,
                },
                },
                heikinashi: {
                lineColor: "#FB1809",
                color: "#FB1809",
                upColor: "#4EA64A",
                upLineColor: "#4EA64A",
                },
                column: {
                color: "#435564",
                },
                bb: {
                lineWidth: 1,
                lineColor: "#20a0b1",
                bottomLine: {
                    styles: {
                    lineWidth: 0.5,
                    lineColor: "#000000",
                    },
                },
                topLine: {
                    styles: {
                    lineWidth: 0.5,
                    lineColor: "#000000",
                    },
                },
                },
            },

            xAxis: {
                lineWidth: 0.1,
                tickColor: "#2f2952",
                crosshair: {
                color: "#8e8aac",
                dashStyle: "dash",
                },
            },

            yAxis: [
                {
                labels: {
                    align: "right",
                    x: -2,
                },
                height: "99%",
                crosshair: {
                    dashStyle: "dash",
                    color: "#696777",
                },

                resize: {
                    enabled: true,
                    lineWidth: 2,
                    lineColor: "#1d1c30",
                },
                gridLineColor: "#201d3a",
                lineWidth: 0,
                visible: true,
                },
                {
                labels: {
                    align: "right",
                    x: -3,
                },
                top: "69%",
                height: "31%",
                offset: 0,
                lineWidth: 0,
                crosshair: false,
                gridLineColor: "#201d3a",
                visible: true,
                },
            ],

            tooltip: {
                split: true,
                shadow: false,
                shape: "rect",
                valueDecimals: 2,

                positioner: function (width, height, point) {
                var chart = this.chart,
                    position;
                if (point.isHeader) {
                    position = {
                    x: Math.max(
                        // Left side limit
                        chart.plotLeft,
                        Math.min(
                        point.plotX + chart.plotLeft - width / 2,
                        // Right side limit
                        chart.chartWidth - width - chart.marginRight
                        )
                    ),
                    y: point.plotY,
                    };
                } else {
                    position = {
                    x: point.series.chart.plotLeft,
                    y: point.series.yAxis.top - chart.plotTop,
                    };
                }
                return position;
                },
            },

            stockTools: {
                gui: {
                enabled: false,
                },
            },

            navigator: {
                enabled: true,
                height: 50,
                margin: 10,
                outlineColor: "#8380a5",
                handles: {
                backgroundColor: "#8380a5",
                borderColor: "#e9d5d5",
                },
                xAxis: {
                gridLineColor: "#8380a5",
                },
            },

            scrollbar: {
                barBackgroundColor: "#8380a5",
                barBorderColor: "#8380a5",
                barBorderRadius: 8,
                buttonArrowColor: "#fff",
                buttonBackgroundColor: "#405466",
                rifleColor: "#fff",
                trackBackgroundColor: "#e9d5d5",
            },

            credits: {
                enabled: false,
            },
        });
        
    });