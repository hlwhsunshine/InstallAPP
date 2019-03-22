package com.netease.nis.wrapper;

import java.io.Serializable;
import java.util.ArrayList;

public class ProtoData {

    public static class ActExit {
        public int code;
        public String mac = "";

        @Override
        public String toString() {
            return "ActExit{" +
                    "code=" + code +
                    ", mac='" + mac + '\'' +
                    '}';
        }
    }

    public static class AdbInfo implements Serializable {
        private static final long serialVersionUID = 1;
        public String pid = "";
        public String prot = "";
        public String serial = "";
        public String vid = "";

        @Override
        public String toString() {
            return "AdbInfo{" +
                    "pid='" + pid + '\'' +
                    ", prot='" + prot + '\'' +
                    ", serial='" + serial + '\'' +
                    ", vid='" + vid + '\'' +
                    '}';
        }
    }

    public static class Asset {
        public String md5 = "";
        public int size = 0;
        public String url = "";

        @Override
        public String toString() {
            return "Asset{" +
                    "md5='" + md5 + '\'' +
                    ", size=" + size +
                    ", url='" + url + '\'' +
                    '}';
        }
    }

    public static class CpuInfo implements Serializable {
        private static final long serialVersionUID = 1;
        public String arch = "";
        public int cores = 1;
        public String hardware = "";
        public String processor = "";

        @Override
        public String toString() {
            return "CpuInfo{" +
                    "arch='" + arch + '\'' +
                    ", cores=" + cores +
                    ", hardware='" + hardware + '\'' +
                    ", processor='" + processor + '\'' +
                    '}';
        }
    }

    public static class DeviceSolution {
        public String productId = "";
        public String productName = "";
        /* renamed from: su */
        public Asset f4066su;
        public String suPath = "";
        public boolean support = true;

        @Override
        public String toString() {
            return "DeviceSolution{" +
                    "productId='" + productId + '\'' +
                    ", productName='" + productName + '\'' +
                    ", f4066su=" + f4066su +
                    ", suPath='" + suPath + '\'' +
                    ", support=" + support +
                    '}';
        }
    }

    public static class PhoneId implements Serializable {
        private static final long serialVersionUID = 1;
        public String imsi = "";
        public String mac = "";
        public String packageName = "";
        public String phimei = "";
        public String phimeiex = "";
        public String phsn = "";
        public String qimei = "";
        public String rid = "";

        @Override
        public String toString() {
            return "PhoneId{" +
                    "imsi='" + imsi + '\'' +
                    ", mac='" + mac + '\'' +
                    ", packageName='" + packageName + '\'' +
                    ", phimei='" + phimei + '\'' +
                    ", phimeiex='" + phimeiex + '\'' +
                    ", phsn='" + phsn + '\'' +
                    ", qimei='" + qimei + '\'' +
                    ", rid='" + rid + '\'' +
                    '}';
        }
    }

    public static class PhoneInfo implements Serializable {
        private static final long serialVersionUID = 1;
        public AdbInfo adb = new AdbInfo();
        public String androidVersion = "";
        public String buildDescription = "";
        public String buildFingerPrint = "";
        public String buildId = "";
        public String buildVersionSdk = "";
        public CpuInfo cpuInfo = new CpuInfo();
        public String hardware = "";
        public String kernel = "";
        public String phoneHardware = "";
        public String phoneId = "";
        public String productBoard = "";
        public String productBrand = "";
        public String productDevice = "";
        public String productId = "";
        public String productManufacturer = "";
        public String productModel = "";
        public String region = "";
        public String resolution = "";

        @Override
        public String toString() {
            return "PhoneInfo{" +
                    "adb=" + adb +
                    ", androidVersion='" + androidVersion + '\'' +
                    ", buildDescription='" + buildDescription + '\'' +
                    ", buildFingerPrint='" + buildFingerPrint + '\'' +
                    ", buildId='" + buildId + '\'' +
                    ", buildVersionSdk='" + buildVersionSdk + '\'' +
                    ", cpuInfo=" + cpuInfo +
                    ", hardware='" + hardware + '\'' +
                    ", kernel='" + kernel + '\'' +
                    ", phoneHardware='" + phoneHardware + '\'' +
                    ", phoneId='" + phoneId + '\'' +
                    ", productBoard='" + productBoard + '\'' +
                    ", productBrand='" + productBrand + '\'' +
                    ", productDevice='" + productDevice + '\'' +
                    ", productId='" + productId + '\'' +
                    ", productManufacturer='" + productManufacturer + '\'' +
                    ", productModel='" + productModel + '\'' +
                    ", region='" + region + '\'' +
                    ", resolution='" + resolution + '\'' +
                    '}';
        }
    }

    public static class QueryRootingResult {
        public String neo = "";
        public String productId = "";
        public String productName = "";
        public int result = 0;
        public Asset rootEngine;
        public ArrayList solus = new ArrayList();
        /* renamed from: su */
        public Asset f4067su;

        @Override
        public String toString() {
            return "QueryRootingResult{" +
                    "neo='" + neo + '\'' +
                    ", productId='" + productId + '\'' +
                    ", productName='" + productName + '\'' +
                    ", result=" + result +
                    ", rootEngine=" + rootEngine +
                    ", solus=" + solus +
                    ", f4067su=" + f4067su +
                    '}';
        }
    }

    public enum QuerySupportResult {
        Support,
        Unsupport,
        Fail
    }

    public static class RootResult implements Serializable {
        public static final int FLAG_ROOT_BY_SUPPORT_TIP = 8;
        private static final long serialVersionUID = 1;
        public int flag = 0;
        public PhoneId phoneId = new PhoneId();
        public PhoneInfo phoneInfo = new PhoneInfo();
        public int totalTimeUsed;
        public TriedSolutions triedSolutions = new TriedSolutions();

        @Override
        public String toString() {
            return "RootResult{" +
                    "flag=" + flag +
                    ", phoneId=" + phoneId +
                    ", phoneInfo=" + phoneInfo +
                    ", totalTimeUsed=" + totalTimeUsed +
                    ", triedSolutions=" + triedSolutions +
                    '}';
        }
    }

    public static class RootSolution {
        /* renamed from: id */
        public int f4068id;
        public String md5 = "";
        public String param = "";
        public String type = "lua";
        public String url = "";

        @Override
        public String toString() {
            return "RootSolution{" +
                    "f4068id=" + f4068id +
                    ", md5='" + md5 + '\'' +
                    ", param='" + param + '\'' +
                    ", type='" + type + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }

    public static class RootingDev {
        public boolean inited = false;
        public PhoneId phoneId = new PhoneId();
        public PhoneInfo phoneInfo = new PhoneInfo();

        @Override
        public String toString() {
            return "RootingDev{" +
                    "inited=" + inited +
                    ", phoneId=" + phoneId +
                    ", phoneInfo=" + phoneInfo +
                    '}';
        }
    }

    public static class ServerConf {
        public int val;

        @Override
        public String toString() {
            return "ServerConf{" +
                    "val=" + val +
                    '}';
        }
    }

    public static class Tried implements Serializable {
        private static final long serialVersionUID = 1;
        public String description = "";
        /* renamed from: id */
        public int f4069id;
        public int result;
        public int timeUsed;

        @Override
        public String toString() {
            return "Tried{" +
                    "description='" + description + '\'' +
                    ", f4069id=" + f4069id +
                    ", result=" + result +
                    ", timeUsed=" + timeUsed +
                    '}';
        }
    }

    public static class TriedSolutions implements Serializable {
        private static final long serialVersionUID = 1;
        public boolean rooted;
        public ArrayList triedList = new ArrayList();

        @Override
        public String toString() {
            return "TriedSolutions{" +
                    "rooted=" + rooted +
                    ", triedList=" + triedList +
                    '}';
        }
    }

    public static class UnrootResult {
        public String description = "";
        public PhoneId phoneId = new PhoneId();
        public int result;

        @Override
        public String toString() {
            return "UnrootResult{" +
                    "description='" + description + '\'' +
                    ", phoneId=" + phoneId +
                    ", result=" + result +
                    '}';
        }
    }

    public static class UpdateReq {
        public String mac = "";
        public String osArch = "";
        public String osName = "";

        @Override
        public String toString() {
            return "UpdateReq{" +
                    "mac='" + mac + '\'' +
                    ", osArch='" + osArch + '\'' +
                    ", osName='" + osName + '\'' +
                    '}';
        }
    }

    public static class UpdateResp {
        public boolean isSuccess;
        public String kinguserMd5 = "";
        public String kinguserUrl = "";
        public String md5 = "";
        public int size;
        public String suPackageName = "";
        public String url = "";
        public int versionCode;
        public String versionName = "";


        @Override
        public String toString() {
            return "UpdateResp{" +
                    "isSuccess=" + isSuccess +
                    ", kinguserMd5='" + kinguserMd5 + '\'' +
                    ", kinguserUrl='" + kinguserUrl + '\'' +
                    ", md5='" + md5 + '\'' +
                    ", size=" + size +
                    ", suPackageName='" + suPackageName + '\'' +
                    ", url='" + url + '\'' +
                    ", versionCode=" + versionCode +
                    ", versionName='" + versionName + '\'' +
                    '}';
        }
    }
}
