package com.lemur.eva.modules.core.systemmaintenance.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProcessUtils {
    private final Logger logger = LoggerFactory.getLogger(ProcessUtils.class);
    private String charset = "gbk";

    public ProcessUtils() {
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    private String getNormalOutput(Process proc, boolean debug) {
        String rv = this.getOutput(proc.getInputStream(), debug);
        if (debug && rv.length() > 0) {
            this.logger.info("exec normal output stream->" + rv);
        }

        return rv;
    }

    private String getErrorOutput(Process proc, boolean debug) {
        String rv = this.getOutput(proc.getErrorStream(), debug);
        if (debug && rv.length() > 0) {
            this.logger.error("exec error stream->" + rv);
        }

        return rv;
    }

    private String getOutput(InputStream is, boolean debug) {
        byte[] buf = new byte[512];
        StringBuilder sb = new StringBuilder();

        try {
            int rv;
            while((rv = is.read(buf)) > 0) {
                sb.append(new String(buf, 0, rv, this.charset));
            }

            return sb.toString();
        } catch (Exception var6) {
            return sb.toString();
        }
    }

    public boolean exec(String cmd) {
        return this.exec(cmd, 1000L, false);
    }

    public boolean exec(String cmd, boolean debug) {
        return this.exec(cmd, 1000L, debug);
    }

    public boolean exec(String cmd, long interval, boolean debug) {
        String[] cmd_arr = crt_cmd(cmd);
        return this.exec(cmd_arr, interval, debug);
    }

    public boolean exec(String[] cmd, long interval, boolean debug) {
        Process proc = null;
        boolean rv = true;

        try {
            proc = Runtime.getRuntime().exec(cmd);
            Thread.sleep(interval);
            String err = this.getErrorOutput(proc, debug);
            if (err.length() > 0) {
                rv = false;
            } else {
                this.getNormalOutput(proc, debug);
            }

            proc.waitFor();
        } catch (Exception var11) {
            this.logger.error(var11.getMessage());
            rv = false;
        } finally {
            destroy(proc);
        }

        return rv;
    }

    public boolean exec_c(String cmd, long interval, boolean debug) {
        boolean rv = true;
        if (debug) {
            this.logger.info(cmd);
        }

        Process proc = null;

        try {
            proc = Runtime.getRuntime().exec(new String[]{"/bin/bash", "-c", cmd});
            Thread.sleep(interval);
            String err = this.getErrorOutput(proc, debug);
            if (err.length() > 0) {
                rv = false;
            } else {
                this.getNormalOutput(proc, debug);
            }

            proc.waitFor();
        } catch (Exception var11) {
            this.logger.error(var11.getMessage());
            rv = false;
        } finally {
            destroy(proc);
        }

        return rv;
    }

    public String exec_reply(String[] cmd, String char_set, long interval) {
        Process proc = null;
        String rv = "";

        String var8;
        try {
            if (cmd != null) {
                this.logger.info(Arrays.toString(cmd));
            }

            proc = Runtime.getRuntime().exec(cmd);
            Thread.sleep(interval);
            rv = this.getNormalOutput(proc, char_set, false);
            proc.waitFor();
            return rv;
        } catch (Exception var12) {
            this.logger.error(var12.getMessage());
            var8 = "error [" + var12.getMessage() + "]eor";
        } finally {
            destroy(proc);
        }

        return var8;
    }

    private String getNormalOutput(Process proc, String char_set, boolean debug) {
        String rv = this.getOutput(proc.getInputStream(), char_set, debug);
        if (debug && rv.length() > 0) {
            this.logger.info("exec normal output stream->" + rv);
        }

        return rv;
    }

    private String getOutput(InputStream is, String char_set, boolean debug) {
        byte[] buf = new byte[512];
        StringBuilder sb = new StringBuilder();

        try {
            int rv;
            while((rv = is.read(buf)) > 0) {
                sb.append(new String(buf, 0, rv, char_set));
            }

            return sb.toString();
        } catch (Exception var7) {
            return sb.toString();
        }
    }

    public String exec_reply(String cmd, long interval) {
        Process proc = null;
        String rv = "";

        try {
            proc = Runtime.getRuntime().exec(new String[]{"/bin/bash", "-c", cmd});
            Thread.sleep(interval);
            rv = this.getNormalOutput(proc, true);
            proc.waitFor();
        } catch (Exception var10) {
            this.logger.error(var10.getMessage());
        } finally {
            destroy(proc);
        }

        return rv;
    }

    public String exec_reply(String[] cmd, long interval) {
        Process proc = null;
        String rv = "";

        String var7;
        try {
            if (cmd == null) {
                var7 = "error [ cmd is null ]eor";
                return var7;
            }
            this.logger.info(Arrays.toString(cmd));

            ProcessBuilder pb = new ProcessBuilder(cmd);
            pb.redirectErrorStream(true);
            proc = pb.start();
            Thread.sleep(interval);
            rv = this.getNormalOutput(proc, false);
            proc.waitFor();
            return rv;
        } catch (Exception e) {
            this.logger.error(e.getMessage());
            var7 = "error [" + e.getMessage() + "]eor";
        } finally {
            destroy(proc);
        }

        return var7;
    }

    public static void destroy(Process proc) {
        if (proc != null) {
            try {
                proc.getErrorStream().close();
            } catch (IOException var4) {
                var4.printStackTrace();
            }

            try {
                proc.getInputStream().close();
            } catch (IOException var3) {
                var3.printStackTrace();
            }

            try {
                proc.getOutputStream().close();
            } catch (IOException var2) {
                var2.printStackTrace();
            }

            proc.destroy();
        }

    }

    public static String[] crt_cmd(String shell) {
        String[] shells = shell.split(" ");
        List<String> cmd = new ArrayList<>();
        StringBuilder tmp = new StringBuilder();
        int index = 0;
        String[] rv = shells;
        int i = shells.length;

        for(int var7 = 0; var7 < i; ++var7) {
            String s = rv[var7];
            if (s.trim().length() != 0) {
                if ((index != 0 || s.contains("'")) && (!s.startsWith("'") || !s.endsWith("'"))) {
                    if (!s.contains("'")) {
                        tmp.append(s);
                    } else {
                        tmp.append(s);
                        ++index;
                    }

                    if (index == 1) {
                        tmp.append(" ");
                    } else if (index == 2) {
                        cmd.add(tmp.toString());
                        index = 0;
                        tmp = new StringBuilder();
                    }
                } else {
                    cmd.add(s);
                }
            }
        }

        rv = new String[cmd.size()];

        for(i = 0; i < cmd.size(); ++i) {
            rv[i] = cmd.get(i);
        }

        return rv;
    }
}