
Ext.Date = (function() {
    
    
    
    
    var utilDate,
        nativeDate = Date,
        stripEscapeRe = /(\\.)/g,
        hourInfoRe = /([gGhHisucUOPZ]|MS)/,
        dateInfoRe = /([djzmnYycU]|MS)/,
        slashRe = /\\/gi,
        numberTokenRe = /\{(\d+)\}/g,
        MSFormatRe = new RegExp('\\/Date\\(([-+])?(\\d+)(?:[+-]\\d{4})?\\)\\/'),
        pad = Ext.String.leftPad,
        code = [
            
            "var me = this, dt, y, m, d, h, i, s, ms, o, O, z, zz, u, v, W, year, jan4, week1monday, daysInMonth, dayMatched,",
            "def = me.defaults,",
            "from = Ext.Number.from,",
            "results = String(input).match(me.parseRegexes[{0}]);",
            
            "if(results){",
            "{1}",
            "if(u != null){",
            
            "v = new Date(u * 1000);",
            
            "}else{",
            "dt = me.clearTime(new Date);",
            "y = from(y, from(def.y, dt.getFullYear()));",
            "m = from(m, from(def.m - 1, dt.getMonth()));",
            "dayMatched = d !== undefined;",
            "d = from(d, from(def.d, dt.getDate()));",
            "if (!dayMatched) {",
            "dt.setDate(1);",
            "dt.setMonth(m);",
            "dt.setFullYear(y);",
            "daysInMonth = me.getDaysInMonth(dt);",
            "if (d > daysInMonth) {",
            "d = daysInMonth;",
            "}",
            "}",
            "h  = from(h, from(def.h, dt.getHours()));",
            "i  = from(i, from(def.i, dt.getMinutes()));",
            "s  = from(s, from(def.s, dt.getSeconds()));",
            "ms = from(ms, from(def.ms, dt.getMilliseconds()));",
            "if(z >= 0 && y >= 0){",
            "v = me.add(new Date(y < 100 ? 100 : y, 0, 1, h, i, s, ms), me.YEAR, y < 100 ? y - 100 : 0);",
            
            "v = !strict? v : (strict === true && (z <= 364 || (me.isLeapYear(v) && z <= 365))? me.add(v, me.DAY, z) : null);",
            "}else if(strict === true && !me.isValid(y, m + 1, d, h, i, s, ms)){",
            
            "v = null;",
            
            "}else{",
            "if (W) {",
            "year = y || (new Date()).getFullYear();",
            "jan4 = new Date(year, 0, 4, 0, 0, 0);",
            "d = jan4.getDay();",
            "week1monday = new Date(jan4.getTime() - ((d === 0 ? 6 : d - 1) * 86400000));",
            "v = Ext.Date.clearTime(new Date(week1monday.getTime() + ((W - 1) * 604800000 + 43200000)));",
            "} else {",
            "v = me.add(new Date(y < 100 ? 100 : y, m, d, h, i, s, ms), me.YEAR, y < 100 ? y - 100 : 0);",
            "}",
            "}",
            "}",
            "}",
            "if(v){",
            
            "if(zz != null){",
            
            "v = me.add(v, me.SECOND, -v.getTimezoneOffset() * 60 - zz);",
            "}else if(o){",
            
            "v = me.add(v, me.MINUTE, -v.getTimezoneOffset() + (sn == '+'? -1 : 1) * (hr * 60 + mn));",
            "}",
            "}",
            "return v;"
        ].join('\n');
    
    
    
    if (!Date.prototype.toISOString) {
        Date.prototype.toISOString = function() {
            var me = this;
            return pad(me.getUTCFullYear(), 4, '0') + '-' + pad(me.getUTCMonth() + 1, 2, '0') + '-' + pad(me.getUTCDate(), 2, '0') + 'T' + pad(me.getUTCHours(), 2, '0') + ':' + pad(me.getUTCMinutes(), 2, '0') + ':' + pad(me.getUTCSeconds(), 2, '0') + '.' + pad(me.getUTCMilliseconds(), 3, '0') + 'Z';
        };
    }
    
    
    
    function xf(format) {
        var args = Array.prototype.slice.call(arguments, 1);
        return format.replace(numberTokenRe, function(m, i) {
            return args[i];
        });
    }
    return utilDate = {
        
        now: nativeDate.now,
        
        
        toString: function(date) {
            if (!date) {
                date = new nativeDate();
            }
            return date.getFullYear() + "-" + pad(date.getMonth() + 1, 2, '0') + "-" + pad(date.getDate(), 2, '0') + "T" + pad(date.getHours(), 2, '0') + ":" + pad(date.getMinutes(), 2, '0') + ":" + pad(date.getSeconds(), 2, '0');
        },
        
        getElapsed: function(dateA, dateB) {
            return Math.abs(dateA - (dateB || utilDate.now()));
        },
        
        useStrict: false,
        
        formatCodeToRegex: function(character, currentGroup) {
            
            var p = utilDate.parseCodes[character];
            if (p) {
                p = typeof p === 'function' ? p() : p;
                utilDate.parseCodes[character] = p;
            }
            
            return p ? Ext.applyIf({
                c: p.c ? xf(p.c, currentGroup || "{0}") : p.c
            }, p) : {
                g: 0,
                c: null,
                s: Ext.String.escapeRegex(character)
            };
        },
        
        
        parseFunctions: {
            "MS": function(input, strict) {
                
                
                var r = (input || '').match(MSFormatRe);
                return r ? new nativeDate(((r[1] || '') + r[2]) * 1) : null;
            },
            "time": function(input, strict) {
                var num = parseInt(input, 10);
                if (num || num === 0) {
                    return new nativeDate(num);
                }
                return null;
            },
            "timestamp": function(input, strict) {
                var num = parseInt(input, 10);
                if (num || num === 0) {
                    return new nativeDate(num * 1000);
                }
                return null;
            }
        },
        parseRegexes: [],
        
        formatFunctions: {
            "MS": function() {
                
                return '\\/Date(' + this.getTime() + ')\\/';
            },
            "time": function() {
                return this.getTime().toString();
            },
            "timestamp": function() {
                return utilDate.format(this, 'U');
            }
        },
        y2kYear: 50,
        
        MILLI: "ms",
        
        SECOND: "s",
        
        MINUTE: "mi",
        
        HOUR: "h",
        
        DAY: "d",
        
        MONTH: "mo",
        
        YEAR: "y",
        
        defaults: {},
        
        
        dayNames: [
            "Sunday",
            "Monday",
            "Tuesday",
            "Wednesday",
            "Thursday",
            "Friday",
            "Saturday"
        ],
        
        
        
        monthNames: [
            "January",
            "February",
            "March",
            "April",
            "May",
            "June",
            "July",
            "August",
            "September",
            "October",
            "November",
            "December"
        ],
        
        
        
        monthNumbers: {
            January: 0,
            Jan: 0,
            February: 1,
            Feb: 1,
            March: 2,
            Mar: 2,
            April: 3,
            Apr: 3,
            May: 4,
            June: 5,
            Jun: 5,
            July: 6,
            Jul: 6,
            August: 7,
            Aug: 7,
            September: 8,
            Sep: 8,
            October: 9,
            Oct: 9,
            November: 10,
            Nov: 10,
            December: 11,
            Dec: 11
        },
        
        
        
        defaultFormat: "m/d/Y",
        
        
        
        getShortMonthName: function(month) {
            return utilDate.monthNames[month].substring(0, 3);
        },
        
        
        
        getShortDayName: function(day) {
            return utilDate.dayNames[day].substring(0, 3);
        },
        
        
        
        getMonthNumber: function(name) {
            
            return utilDate.monthNumbers[name.substring(0, 1).toUpperCase() + name.substring(1, 3).toLowerCase()];
        },
        
        
        formatContainsHourInfo: function(format) {
            return hourInfoRe.test(format.replace(stripEscapeRe, ''));
        },
        
        formatContainsDateInfo: function(format) {
            return dateInfoRe.test(format.replace(stripEscapeRe, ''));
        },
        
        unescapeFormat: function(format) {
            
            
            
            return format.replace(slashRe, '');
        },
        
        formatCodes: {
            d: "Ext.String.leftPad(m.getDate(), 2, '0')",
            D: "Ext.Date.getShortDayName(m.getDay())",
            
            j: "m.getDate()",
            l: "Ext.Date.dayNames[m.getDay()]",
            N: "(m.getDay() ? m.getDay() : 7)",
            S: "Ext.Date.getSuffix(m)",
            w: "m.getDay()",
            z: "Ext.Date.getDayOfYear(m)",
            W: "Ext.String.leftPad(Ext.Date.getWeekOfYear(m), 2, '0')",
            F: "Ext.Date.monthNames[m.getMonth()]",
            m: "Ext.String.leftPad(m.getMonth() + 1, 2, '0')",
            M: "Ext.Date.getShortMonthName(m.getMonth())",
            
            n: "(m.getMonth() + 1)",
            t: "Ext.Date.getDaysInMonth(m)",
            L: "(Ext.Date.isLeapYear(m) ? 1 : 0)",
            o: "(m.getFullYear() + (Ext.Date.getWeekOfYear(m) == 1 && m.getMonth() > 0 ? +1 : (Ext.Date.getWeekOfYear(m) >= 52 && m.getMonth() < 11 ? -1 : 0)))",
            Y: "Ext.String.leftPad(m.getFullYear(), 4, '0')",
            y: "('' + m.getFullYear()).substring(2, 4)",
            a: "(m.getHours() < 12 ? 'am' : 'pm')",
            A: "(m.getHours() < 12 ? 'AM' : 'PM')",
            g: "((m.getHours() % 12) ? m.getHours() % 12 : 12)",
            G: "m.getHours()",
            h: "Ext.String.leftPad((m.getHours() % 12) ? m.getHours() % 12 : 12, 2, '0')",
            H: "Ext.String.leftPad(m.getHours(), 2, '0')",
            i: "Ext.String.leftPad(m.getMinutes(), 2, '0')",
            s: "Ext.String.leftPad(m.getSeconds(), 2, '0')",
            u: "Ext.String.leftPad(m.getMilliseconds(), 3, '0')",
            O: "Ext.Date.getGMTOffset(m)",
            P: "Ext.Date.getGMTOffset(m, true)",
            T: "Ext.Date.getTimezone(m)",
            Z: "(m.getTimezoneOffset() * -60)",
            c: function() {
                
                var c = "Y-m-dTH:i:sP",
                    code = [],
                    i,
                    l = c.length,
                    e;
                for (i = 0; i < l; ++i) {
                    e = c.charAt(i);
                    code.push(e === "T" ? "'T'" : utilDate.getFormatCode(e));
                }
                
                return code.join(" + ");
            },
            C: function() {
                
                return 'm.toISOString()';
            },
            U: "Math.round(m.getTime() / 1000)"
        },
        
        isValid: function(y, m, d, h, i, s, ms) {
            
            h = h || 0;
            i = i || 0;
            s = s || 0;
            ms = ms || 0;
            
            var dt = utilDate.add(new nativeDate(y < 100 ? 100 : y,m - 1,d,h,i,s,ms), utilDate.YEAR, y < 100 ? y - 100 : 0);
            return y === dt.getFullYear() && m === dt.getMonth() + 1 && d === dt.getDate() && h === dt.getHours() && i === dt.getMinutes() && s === dt.getSeconds() && ms === dt.getMilliseconds();
        },
        
        parse: function(input, format, strict) {
            var p = utilDate.parseFunctions;
            if (p[format] == null) {
                utilDate.createParser(format);
            }
            return p[format].call(utilDate, input, Ext.isDefined(strict) ? strict : utilDate.useStrict);
        },
        
        parseDate: function(input, format, strict) {
            return utilDate.parse(input, format, strict);
        },
        
        getFormatCode: function(character) {
            var f = utilDate.formatCodes[character];
            if (f) {
                f = typeof f === 'function' ? f() : f;
                utilDate.formatCodes[character] = f;
            }
            
            
            return f || ("'" + Ext.String.escape(character) + "'");
        },
        
        createFormat: function(format) {
            var code = [],
                special = false,
                ch = '',
                i;
            for (i = 0; i < format.length; ++i) {
                ch = format.charAt(i);
                if (!special && ch === "\\") {
                    special = true;
                } else if (special) {
                    special = false;
                    code.push("'" + Ext.String.escape(ch) + "'");
                } else {
                    if (ch === '\n') {
                        code.push("'\\n'");
                    } else {
                        code.push(utilDate.getFormatCode(ch));
                    }
                }
            }
            utilDate.formatFunctions[format] = Ext.functionFactory("var m=this;return " + code.join('+'));
        },
        
        createParser: function(format) {
            var regexNum = utilDate.parseRegexes.length,
                currentGroup = 1,
                calc = [],
                regex = [],
                special = false,
                ch = "",
                i = 0,
                len = format.length,
                atEnd = [],
                obj;
            for (; i < len; ++i) {
                ch = format.charAt(i);
                if (!special && ch === "\\") {
                    special = true;
                } else if (special) {
                    special = false;
                    regex.push(Ext.String.escape(ch));
                } else {
                    obj = utilDate.formatCodeToRegex(ch, currentGroup);
                    currentGroup += obj.g;
                    regex.push(obj.s);
                    if (obj.g && obj.c) {
                        if (obj.calcAtEnd) {
                            atEnd.push(obj.c);
                        } else {
                            calc.push(obj.c);
                        }
                    }
                }
            }
            calc = calc.concat(atEnd);
            utilDate.parseRegexes[regexNum] = new RegExp("^" + regex.join('') + "$",'i');
            utilDate.parseFunctions[format] = Ext.functionFactory("input", "strict", xf(code, regexNum, calc.join('')));
        },
        
        parseCodes: {
            
            d: {
                g: 1,
                c: "d = parseInt(results[{0}], 10);\n",
                s: "(3[0-1]|[1-2][0-9]|0[1-9])"
            },
            
            j: {
                g: 1,
                c: "d = parseInt(results[{0}], 10);\n",
                s: "(3[0-1]|[1-2][0-9]|[1-9])"
            },
            
            D: function() {
                for (var a = [],
                    i = 0; i < 7; a.push(utilDate.getShortDayName(i)) , ++i){}
                
                return {
                    g: 0,
                    c: null,
                    s: "(?:" + a.join("|") + ")"
                };
            },
            l: function() {
                return {
                    g: 0,
                    c: null,
                    s: "(?:" + utilDate.dayNames.join("|") + ")"
                };
            },
            N: {
                g: 0,
                c: null,
                s: "[1-7]"
            },
            
            
            S: {
                g: 0,
                c: null,
                s: "(?:st|nd|rd|th)"
            },
            
            w: {
                g: 0,
                c: null,
                s: "[0-6]"
            },
            
            z: {
                g: 1,
                c: "z = parseInt(results[{0}], 10);\n",
                s: "(\\d{1,3})"
            },
            
            W: {
                g: 1,
                c: "W = parseInt(results[{0}], 10);\n",
                s: "(\\d{2})"
            },
            
            F: function() {
                return {
                    g: 1,
                    c: "m = parseInt(me.getMonthNumber(results[{0}]), 10);\n",
                    
                    s: "(" + utilDate.monthNames.join("|") + ")"
                };
            },
            M: function() {
                for (var a = [],
                    i = 0; i < 12; a.push(utilDate.getShortMonthName(i)) , ++i){}
                
                return Ext.applyIf({
                    s: "(" + a.join("|") + ")"
                }, utilDate.formatCodeToRegex("F"));
            },
            m: {
                g: 1,
                c: "m = parseInt(results[{0}], 10) - 1;\n",
                s: "(1[0-2]|0[1-9])"
            },
            
            n: {
                g: 1,
                c: "m = parseInt(results[{0}], 10) - 1;\n",
                s: "(1[0-2]|[1-9])"
            },
            
            t: {
                g: 0,
                c: null,
                s: "(?:\\d{2})"
            },
            
            L: {
                g: 0,
                c: null,
                s: "(?:1|0)"
            },
            o: {
                g: 1,
                c: "y = parseInt(results[{0}], 10);\n",
                s: "(\\d{4})"
            },
            
            Y: {
                g: 1,
                c: "y = parseInt(results[{0}], 10);\n",
                s: "(\\d{4})"
            },
            
            y: {
                g: 1,
                c: "var ty = parseInt(results[{0}], 10);\n" + "y = ty > me.y2kYear ? 1900 + ty : 2000 + ty;\n",
                
                s: "(\\d{1,2})"
            },
            
            
            a: {
                g: 1,
                c: "if (/(am)/i.test(results[{0}])) {\n" + "if (!h || h == 12) { h = 0; }\n" + "} else { if (!h || h < 12) { h = (h || 0) + 12; }}",
                s: "(am|pm|AM|PM)",
                calcAtEnd: true
            },
            
            
            A: {
                g: 1,
                c: "if (/(am)/i.test(results[{0}])) {\n" + "if (!h || h == 12) { h = 0; }\n" + "} else { if (!h || h < 12) { h = (h || 0) + 12; }}",
                s: "(AM|PM|am|pm)",
                calcAtEnd: true
            },
            
            g: {
                g: 1,
                c: "h = parseInt(results[{0}], 10);\n",
                s: "(1[0-2]|[0-9])"
            },
            
            G: {
                g: 1,
                c: "h = parseInt(results[{0}], 10);\n",
                s: "(2[0-3]|1[0-9]|[0-9])"
            },
            
            h: {
                g: 1,
                c: "h = parseInt(results[{0}], 10);\n",
                s: "(1[0-2]|0[1-9])"
            },
            
            H: {
                g: 1,
                c: "h = parseInt(results[{0}], 10);\n",
                s: "(2[0-3]|[0-1][0-9])"
            },
            
            i: {
                g: 1,
                c: "i = parseInt(results[{0}], 10);\n",
                s: "([0-5][0-9])"
            },
            
            s: {
                g: 1,
                c: "s = parseInt(results[{0}], 10);\n",
                s: "([0-5][0-9])"
            },
            
            u: {
                g: 1,
                c: "ms = results[{0}]; ms = parseInt(ms, 10)/Math.pow(10, ms.length - 3);\n",
                s: "(\\d+)"
            },
            
            O: {
                g: 1,
                c: [
                    "o = results[{0}];",
                    "var sn = o.substring(0,1),",
                    
                    "hr = o.substring(1,3)*1 + Math.floor(o.substring(3,5) / 60),",
                    
                    "mn = o.substring(3,5) % 60;",
                    
                    "o = ((-12 <= (hr*60 + mn)/60) && ((hr*60 + mn)/60 <= 14))? (sn + Ext.String.leftPad(hr, 2, '0') + Ext.String.leftPad(mn, 2, '0')) : null;\n"
                ].
                join("\n"),
                s: "([+-]\\d{4})"
            },
            
            P: {
                g: 1,
                c: [
                    "o = results[{0}];",
                    "var sn = o.substring(0,1),",
                    
                    "hr = o.substring(1,3)*1 + Math.floor(o.substring(4,6) / 60),",
                    
                    "mn = o.substring(4,6) % 60;",
                    
                    "o = ((-12 <= (hr*60 + mn)/60) && ((hr*60 + mn)/60 <= 14))? (sn + Ext.String.leftPad(hr, 2, '0') + Ext.String.leftPad(mn, 2, '0')) : null;\n"
                ].
                join("\n"),
                s: "([+-]\\d{2}:\\d{2})"
            },
            
            T: {
                g: 0,
                c: null,
                s: "[A-Z]{1,5}"
            },
            
            Z: {
                g: 1,
                c: "zz = results[{0}] * 1;\n" + 
                "zz = (-43200 <= zz && zz <= 50400)? zz : null;\n",
                s: "([+-]?\\d{1,5})"
            },
            
            c: function() {
                var calc = [],
                    arr = [
                        utilDate.formatCodeToRegex("Y", 1),
                        
                        utilDate.formatCodeToRegex("m", 2),
                        
                        utilDate.formatCodeToRegex("d", 3),
                        
                        utilDate.formatCodeToRegex("H", 4),
                        
                        utilDate.formatCodeToRegex("i", 5),
                        
                        utilDate.formatCodeToRegex("s", 6),
                        
                        {
                            c: "ms = results[7] || '0'; ms = parseInt(ms, 10)/Math.pow(10, ms.length - 3);\n"
                        },
                        
                        {
                            c: [
                                
                                "if(results[8]) {",
                                
                                "if(results[8] == 'Z'){",
                                "zz = 0;",
                                
                                "}else if (results[8].indexOf(':') > -1){",
                                utilDate.formatCodeToRegex("P", 8).c,
                                
                                "}else{",
                                utilDate.formatCodeToRegex("O", 8).c,
                                
                                "}",
                                "}"
                            ].join('\n')
                        }
                    ],
                    i, l;
                for (i = 0 , l = arr.length; i < l; ++i) {
                    calc.push(arr[i].c);
                }
                return {
                    g: 1,
                    c: calc.join(""),
                    s: [
                        arr[0].s,
                        
                        "(?:",
                        "-",
                        arr[1].s,
                        
                        "(?:",
                        "-",
                        arr[2].s,
                        
                        "(?:",
                        "(?:T| )?",
                        
                        arr[3].s,
                        ":",
                        arr[4].s,
                        
                        "(?::",
                        arr[5].s,
                        ")?",
                        
                        "(?:(?:\\.|,)(\\d+))?",
                        
                        "(Z|(?:[-+]\\d{2}(?::)?\\d{2}))?",
                        
                        ")?",
                        ")?",
                        ")?"
                    ].join("")
                };
            },
            U: {
                g: 1,
                c: "u = parseInt(results[{0}], 10);\n",
                s: "(-?\\d+)"
            }
        },
        
        
        
        dateFormat: function(date, format) {
            return utilDate.format(date, format);
        },
        
        isEqual: function(date1, date2) {
            
            if (date1 && date2) {
                return (date1.getTime() === date2.getTime());
            }
            
            return !(date1 || date2);
        },
        
        format: function(date, format) {
            var formatFunctions = utilDate.formatFunctions;
            if (!Ext.isDate(date)) {
                return '';
            }
            if (formatFunctions[format] == null) {
                utilDate.createFormat(format);
            }
            return formatFunctions[format].call(date) + '';
        },
        
        getTimezone: function(date) {
            
            
            
            
            
            
            
            
            
            
            
            
            return date.toString().replace(/^.* (?:\((.*)\)|([A-Z]{1,5})(?:[\-+][0-9]{4})?(?: -?\d+)?)$/, "$1$2").replace(/[^A-Z]/g, "");
        },
        
        getGMTOffset: function(date, colon) {
            var offset = date.getTimezoneOffset();
            return (offset > 0 ? "-" : "+") + Ext.String.leftPad(Math.floor(Math.abs(offset) / 60), 2, "0") + (colon ? ":" : "") + Ext.String.leftPad(Math.abs(offset % 60), 2, "0");
        },
        
        getDayOfYear: function(date) {
            var num = 0,
                d = utilDate.clone(date),
                m = date.getMonth(),
                i;
            for (i = 0 , d.setDate(1) , d.setMonth(0); i < m; d.setMonth(++i)) {
                num += utilDate.getDaysInMonth(d);
            }
            return num + date.getDate() - 1;
        },
        
        getWeekOfYear: (function() {
            
            var ms1d = 86400000,
                
                ms7d = 7 * ms1d;
            
            return function(date) {
                
                var DC3 = nativeDate.UTC(date.getFullYear(), date.getMonth(), date.getDate() + 3) / ms1d,
                    
                    AWN = Math.floor(DC3 / 7),
                    
                    Wyr = new nativeDate(AWN * ms7d).getUTCFullYear();
                return AWN - Math.floor(nativeDate.UTC(Wyr, 0, 7) / ms7d) + 1;
            };
        }()),
        
        isLeapYear: function(date) {
            var year = date.getFullYear();
            return !!((year & 3) === 0 && (year % 100 || (year % 400 === 0 && year)));
        },
        
        getFirstDayOfMonth: function(date) {
            var day = (date.getDay() - (date.getDate() - 1)) % 7;
            return (day < 0) ? (day + 7) : day;
        },
        
        getLastDayOfMonth: function(date) {
            return utilDate.getLastDateOfMonth(date).getDay();
        },
        
        getFirstDateOfMonth: function(date) {
            return new nativeDate(date.getFullYear(),date.getMonth(),1);
        },
        
        getLastDateOfMonth: function(date) {
            return new nativeDate(date.getFullYear(),date.getMonth(),utilDate.getDaysInMonth(date));
        },
        
        getDaysInMonth: (function() {
            var daysInMonth = [
                    31,
                    28,
                    31,
                    30,
                    31,
                    30,
                    31,
                    31,
                    30,
                    31,
                    30,
                    31
                ];
            return function(date) {
                
                var m = date.getMonth();
                return m === 1 && utilDate.isLeapYear(date) ? 29 : daysInMonth[m];
            };
        }()),
        
        
        getSuffix: function(date) {
            switch (date.getDate()) {
                case 1:
                case 21:
                case 31:
                    return "st";
                case 2:
                case 22:
                    return "nd";
                case 3:
                case 23:
                    return "rd";
                default:
                    return "th";
            }
        },
        
        
        clone: function(date) {
            return new nativeDate(date.getTime());
        },
        
        isDST: function(date) {
            
            
            return new nativeDate(date.getFullYear(),0,1).getTimezoneOffset() !== date.getTimezoneOffset();
        },
        
        clearTime: function(date, clone) {
            if (clone) {
                return utilDate.clearTime(utilDate.clone(date));
            }
            
            var d = date.getDate(),
                hr, c;
            
            date.setHours(0);
            date.setMinutes(0);
            date.setSeconds(0);
            date.setMilliseconds(0);
            if (date.getDate() !== d) {
                
                
                
                
                for (hr = 1 , c = utilDate.add(date, utilDate.HOUR, hr); c.getDate() !== d; hr++ , c = utilDate.add(date, utilDate.HOUR, hr)){}
                date.setDate(d);
                date.setHours(c.getHours());
            }
            return date;
        },
        
        add: function(date, interval, value) {
            var d = utilDate.clone(date),
                day, decimalValue,
                base = 0;
            if (!interval || value === 0) {
                return d;
            }
            decimalValue = value - parseInt(value, 10);
            value = parseInt(value, 10);
            if (value) {
                switch (interval.toLowerCase()) {
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    case utilDate.MILLI:
                        d.setTime(d.getTime() + value);
                        break;
                    case utilDate.SECOND:
                        d.setTime(d.getTime() + value * 1000);
                        break;
                    case utilDate.MINUTE:
                        d.setTime(d.getTime() + value * 60 * 1000);
                        break;
                    case utilDate.HOUR:
                        d.setTime(d.getTime() + value * 60 * 60 * 1000);
                        break;
                    case utilDate.DAY:
                        d.setDate(d.getDate() + value);
                        break;
                    case utilDate.MONTH:
                        day = date.getDate();
                        if (day > 28) {
                            day = Math.min(day, utilDate.getLastDateOfMonth(utilDate.add(utilDate.getFirstDateOfMonth(date), utilDate.MONTH, value)).getDate());
                        };
                        d.setDate(day);
                        d.setMonth(date.getMonth() + value);
                        break;
                    case utilDate.YEAR:
                        day = date.getDate();
                        if (day > 28) {
                            day = Math.min(day, utilDate.getLastDateOfMonth(utilDate.add(utilDate.getFirstDateOfMonth(date), utilDate.YEAR, value)).getDate());
                        };
                        d.setDate(day);
                        d.setFullYear(date.getFullYear() + value);
                        break;
                }
            }
            if (decimalValue) {
                switch (interval.toLowerCase()) {
                    case utilDate.MILLI:
                        base = 1;
                        break;
                    case utilDate.SECOND:
                        base = 1000;
                        break;
                    case utilDate.MINUTE:
                        base = 1000 * 60;
                        break;
                    case utilDate.HOUR:
                        base = 1000 * 60 * 60;
                        break;
                    case utilDate.DAY:
                        base = 1000 * 60 * 60 * 24;
                        break;
                    case utilDate.MONTH:
                        day = utilDate.getDaysInMonth(d);
                        base = 1000 * 60 * 60 * 24 * day;
                        break;
                    case utilDate.YEAR:
                        day = (utilDate.isLeapYear(d) ? 366 : 365);
                        base = 1000 * 60 * 60 * 24 * day;
                        break;
                }
                if (base) {
                    d.setTime(d.getTime() + base * decimalValue);
                }
            }
            return d;
        },
        
        subtract: function(date, interval, value) {
            return utilDate.add(date, interval, -value);
        },
        
        between: function(date, start, end) {
            var t = date.getTime();
            return start.getTime() <= t && t <= end.getTime();
        },
        
        compat: function() {
            var p,
                statics = [
                    'useStrict',
                    'formatCodeToRegex',
                    'parseFunctions',
                    'parseRegexes',
                    'formatFunctions',
                    'y2kYear',
                    'MILLI',
                    'SECOND',
                    'MINUTE',
                    'HOUR',
                    'DAY',
                    'MONTH',
                    'YEAR',
                    'defaults',
                    'dayNames',
                    'monthNames',
                    'monthNumbers',
                    'getShortMonthName',
                    'getShortDayName',
                    'getMonthNumber',
                    'formatCodes',
                    'isValid',
                    'parseDate',
                    'getFormatCode',
                    'createFormat',
                    'createParser',
                    'parseCodes'
                ],
                proto = [
                    'dateFormat',
                    'format',
                    'getTimezone',
                    'getGMTOffset',
                    'getDayOfYear',
                    'getWeekOfYear',
                    'isLeapYear',
                    'getFirstDayOfMonth',
                    'getLastDayOfMonth',
                    'getDaysInMonth',
                    'getSuffix',
                    'clone',
                    'isDST',
                    'clearTime',
                    'add',
                    'between'
                ],
                sLen = statics.length,
                pLen = proto.length,
                stat, prot, s;
            
            for (s = 0; s < sLen; s++) {
                stat = statics[s];
                nativeDate[stat] = utilDate[stat];
            }
            
            for (p = 0; p < pLen; p++) {
                prot = proto[p];
                nativeDate.prototype[prot] = function() {
                    var args = Array.prototype.slice.call(arguments);
                    args.unshift(this);
                    return utilDate[prot].apply(utilDate, args);
                };
            }
        },
        
        diff: function(min, max, unit) {
            var est,
                diff = +max - min;
            switch (unit) {
                case utilDate.MILLI:
                    return diff;
                case utilDate.SECOND:
                    return Math.floor(diff / 1000);
                case utilDate.MINUTE:
                    return Math.floor(diff / 60000);
                case utilDate.HOUR:
                    return Math.floor(diff / 3600000);
                case utilDate.DAY:
                    return Math.floor(diff / 86400000);
                case 'w':
                    return Math.floor(diff / 604800000);
                case utilDate.MONTH:
                    est = (max.getFullYear() * 12 + max.getMonth()) - (min.getFullYear() * 12 + min.getMonth());
                    if (utilDate.add(min, unit, est) > max) {
                        return est - 1;
                    } else {
                        return est;
                    };
                case utilDate.YEAR:
                    est = max.getFullYear() - min.getFullYear();
                    if (utilDate.add(min, unit, est) > max) {
                        return est - 1;
                    } else {
                        return est;
                    };
            }
        },
        
        align: function(date, unit, step) {
            var num = new nativeDate(+date);
            switch (unit.toLowerCase()) {
                case utilDate.MILLI:
                    return num;
                    break;
                case utilDate.SECOND:
                    num.setUTCSeconds(num.getUTCSeconds() - num.getUTCSeconds() % step);
                    num.setUTCMilliseconds(0);
                    return num;
                    break;
                case utilDate.MINUTE:
                    num.setUTCMinutes(num.getUTCMinutes() - num.getUTCMinutes() % step);
                    num.setUTCSeconds(0);
                    num.setUTCMilliseconds(0);
                    return num;
                    break;
                case utilDate.HOUR:
                    num.setUTCHours(num.getUTCHours() - num.getUTCHours() % step);
                    num.setUTCMinutes(0);
                    num.setUTCSeconds(0);
                    num.setUTCMilliseconds(0);
                    return num;
                    break;
                case utilDate.DAY:
                    if (step === 7 || step === 14) {
                        num.setUTCDate(num.getUTCDate() - num.getUTCDay() + 1);
                    };
                    num.setUTCHours(0);
                    num.setUTCMinutes(0);
                    num.setUTCSeconds(0);
                    num.setUTCMilliseconds(0);
                    return num;
                    break;
                case utilDate.MONTH:
                    num.setUTCMonth(num.getUTCMonth() - (num.getUTCMonth() - 1) % step, 1);
                    num.setUTCHours(0);
                    num.setUTCMinutes(0);
                    num.setUTCSeconds(0);
                    num.setUTCMilliseconds(0);
                    return num;
                    break;
                case utilDate.YEAR:
                    num.setUTCFullYear(num.getUTCFullYear() - num.getUTCFullYear() % step, 1, 1);
                    num.setUTCHours(0);
                    num.setUTCMinutes(0);
                    num.setUTCSeconds(0);
                    num.setUTCMilliseconds(0);
                    return date;
                    break;
            }
        }
    };
}());