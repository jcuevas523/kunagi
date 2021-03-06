// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.legacy.generator.EntityGenerator










package scrum.server.files;

import java.util.*;
import ilarkesto.core.base.Utl;
import ilarkesto.core.logging.Log;
import ilarkesto.persistence.ADatob;
import ilarkesto.persistence.AEntity;
import ilarkesto.persistence.AStructure;
import ilarkesto.auth.AUser;
import ilarkesto.core.base.Str;
import ilarkesto.core.persistance.EntityDoesNotExistException;

public abstract class GFile
            extends AEntity
            implements ilarkesto.auth.ViewProtected<scrum.server.admin.User>, java.lang.Comparable<File>, ilarkesto.search.Searchable {

    protected static final ilarkesto.core.logging.Log log = ilarkesto.core.logging.Log.get(File.class);

    // --- AEntity ---

    public final scrum.server.files.FileDao getDao() {
        return fileDao;
    }

    protected void repairDeadDatob(ADatob datob) {
    }

    @Override
    public void storeProperties(Map<String, String> properties) {
        super.storeProperties(properties);
        properties.put("projectId", ilarkesto.core.persistance.Persistence.propertyAsString(this.projectId));
        properties.put("filename", ilarkesto.core.persistance.Persistence.propertyAsString(this.filename));
        properties.put("uploadTime", ilarkesto.core.persistance.Persistence.propertyAsString(this.uploadTime));
        properties.put("label", ilarkesto.core.persistance.Persistence.propertyAsString(this.label));
        properties.put("number", ilarkesto.core.persistance.Persistence.propertyAsString(this.number));
        properties.put("note", ilarkesto.core.persistance.Persistence.propertyAsString(this.note));
    }

    public int compareTo(File other) {
        return toString().toLowerCase().compareTo(other.toString().toLowerCase());
    }

    private static final ilarkesto.core.logging.Log LOG = ilarkesto.core.logging.Log.get(GFile.class);

    public static final String TYPE = "file";


    // -----------------------------------------------------------
    // - Searchable
    // -----------------------------------------------------------

    public boolean matchesKey(String key) {
        if (super.matchesKey(key)) return true;
        if (matchesKey(getFilename(), key)) return true;
        if (matchesKey(getLabel(), key)) return true;
        if (matchesKey(getNote(), key)) return true;
        return false;
    }

    // -----------------------------------------------------------
    // - project
    // -----------------------------------------------------------

    private String projectId;

    public final String getProjectId() {
        return this.projectId;
    }

    public final scrum.server.project.Project getProject() {
        try {
            return this.projectId == null ? null : (scrum.server.project.Project) AEntity.getById(this.projectId);
        } catch (ilarkesto.core.persistance.EntityDoesNotExistException ex) {
            throw ex.setCallerInfo("File.project");
        }
    }

    public final void setProject(scrum.server.project.Project project) {
        project = prepareProject(project);
        if (isProject(project)) return;
        setProjectId(project == null ? null : project.getId());
    }

    public final void setProjectId(String id) {
        if (Utl.equals(projectId, id)) return;
        this.projectId = id;
            updateLastModified();
            fireModified("projectId", ilarkesto.core.persistance.Persistence.propertyAsString(this.projectId));
    }

    protected scrum.server.project.Project prepareProject(scrum.server.project.Project project) {
        return project;
    }

    protected void repairDeadProjectReference(String entityId) {
        if (this.projectId == null || entityId.equals(this.projectId)) {
            repairMissingMaster();
        }
    }

    public final boolean isProjectSet() {
        return this.projectId != null;
    }

    public final boolean isProject(scrum.server.project.Project project) {
        if (this.projectId == null && project == null) return true;
        return project != null && project.getId().equals(this.projectId);
    }

    protected final void updateProject(Object value) {
        setProject(value == null ? null : (scrum.server.project.Project)projectDao.getById((String)value));
    }

    // -----------------------------------------------------------
    // - filename
    // -----------------------------------------------------------

    private java.lang.String filename;

    public final java.lang.String getFilename() {
        return filename;
    }

    public final void setFilename(java.lang.String filename) {
        filename = prepareFilename(filename);
        if (isFilename(filename)) return;
        if (filename == null) throw new IllegalArgumentException("Mandatory field can not be set to null: filename");
        this.filename = filename;
            updateLastModified();
            fireModified("filename", ilarkesto.core.persistance.Persistence.propertyAsString(this.filename));
    }

    protected java.lang.String prepareFilename(java.lang.String filename) {
        // filename = Str.removeUnreadableChars(filename);
        return filename;
    }

    public final boolean isFilenameSet() {
        return this.filename != null;
    }

    public final boolean isFilename(java.lang.String filename) {
        if (this.filename == null && filename == null) return true;
        return this.filename != null && this.filename.equals(filename);
    }

    protected final void updateFilename(Object value) {
        setFilename((java.lang.String)value);
    }

    // -----------------------------------------------------------
    // - uploadTime
    // -----------------------------------------------------------

    private ilarkesto.core.time.DateAndTime uploadTime;

    public final ilarkesto.core.time.DateAndTime getUploadTime() {
        return uploadTime;
    }

    public final void setUploadTime(ilarkesto.core.time.DateAndTime uploadTime) {
        uploadTime = prepareUploadTime(uploadTime);
        if (isUploadTime(uploadTime)) return;
        if (uploadTime == null) throw new IllegalArgumentException("Mandatory field can not be set to null: uploadTime");
        this.uploadTime = uploadTime;
            updateLastModified();
            fireModified("uploadTime", ilarkesto.core.persistance.Persistence.propertyAsString(this.uploadTime));
    }

    protected ilarkesto.core.time.DateAndTime prepareUploadTime(ilarkesto.core.time.DateAndTime uploadTime) {
        return uploadTime;
    }

    public final boolean isUploadTimeSet() {
        return this.uploadTime != null;
    }

    public final boolean isUploadTime(ilarkesto.core.time.DateAndTime uploadTime) {
        if (this.uploadTime == null && uploadTime == null) return true;
        return this.uploadTime != null && this.uploadTime.equals(uploadTime);
    }

    protected final void updateUploadTime(Object value) {
        value = value == null ? null : new ilarkesto.core.time.DateAndTime((String)value);
        setUploadTime((ilarkesto.core.time.DateAndTime)value);
    }

    // -----------------------------------------------------------
    // - label
    // -----------------------------------------------------------

    private java.lang.String label;

    public final java.lang.String getLabel() {
        return label;
    }

    public final void setLabel(java.lang.String label) {
        label = prepareLabel(label);
        if (isLabel(label)) return;
        if (label == null) throw new IllegalArgumentException("Mandatory field can not be set to null: label");
        this.label = label;
            updateLastModified();
            fireModified("label", ilarkesto.core.persistance.Persistence.propertyAsString(this.label));
    }

    protected java.lang.String prepareLabel(java.lang.String label) {
        // label = Str.removeUnreadableChars(label);
        return label;
    }

    public final boolean isLabelSet() {
        return this.label != null;
    }

    public final boolean isLabel(java.lang.String label) {
        if (this.label == null && label == null) return true;
        return this.label != null && this.label.equals(label);
    }

    protected final void updateLabel(Object value) {
        setLabel((java.lang.String)value);
    }

    // -----------------------------------------------------------
    // - number
    // -----------------------------------------------------------

    private int number;

    public final int getNumber() {
        return number;
    }

    public final void setNumber(int number) {
        number = prepareNumber(number);
        if (isNumber(number)) return;
        this.number = number;
            updateLastModified();
            fireModified("number", ilarkesto.core.persistance.Persistence.propertyAsString(this.number));
    }

    protected int prepareNumber(int number) {
        return number;
    }

    public final boolean isNumber(int number) {
        return this.number == number;
    }

    protected final void updateNumber(Object value) {
        setNumber((Integer)value);
    }

    // -----------------------------------------------------------
    // - note
    // -----------------------------------------------------------

    private java.lang.String note;

    public final java.lang.String getNote() {
        return note;
    }

    public final void setNote(java.lang.String note) {
        note = prepareNote(note);
        if (isNote(note)) return;
        this.note = note;
            updateLastModified();
            fireModified("note", ilarkesto.core.persistance.Persistence.propertyAsString(this.note));
    }

    protected java.lang.String prepareNote(java.lang.String note) {
        // note = Str.removeUnreadableChars(note);
        return note;
    }

    public final boolean isNoteSet() {
        return this.note != null;
    }

    public final boolean isNote(java.lang.String note) {
        if (this.note == null && note == null) return true;
        return this.note != null && this.note.equals(note);
    }

    protected final void updateNote(Object value) {
        setNote((java.lang.String)value);
    }

    public void updateProperties(Map<String, String> properties) {
        for (Map.Entry<String, String> entry : properties.entrySet()) {
            String property = entry.getKey();
            if (property.equals("id")) continue;
            String value = entry.getValue();
            if (property.equals("projectId")) setProjectId(ilarkesto.core.persistance.Persistence.parsePropertyReference(value));
            if (property.equals("filename")) setFilename(ilarkesto.core.persistance.Persistence.parsePropertyString(value));
            if (property.equals("uploadTime")) setUploadTime(ilarkesto.core.persistance.Persistence.parsePropertyDateAndTime(value));
            if (property.equals("label")) setLabel(ilarkesto.core.persistance.Persistence.parsePropertyString(value));
            if (property.equals("number")) setNumber(ilarkesto.core.persistance.Persistence.parsePropertyint(value));
            if (property.equals("note")) setNote(ilarkesto.core.persistance.Persistence.parsePropertyString(value));
        }
    }

    protected void repairDeadReferences(String entityId) {
        super.repairDeadReferences(entityId);
        repairDeadProjectReference(entityId);
    }

    // --- ensure integrity ---
    @Override
    public void ensureIntegrity() {
        super.ensureIntegrity();
        if (!isProjectSet()) {
            repairMissingMaster();
        }
        try {
            getProject();
        } catch (ilarkesto.core.persistance.EntityDoesNotExistException ex) {
            LOG.info("Repairing dead project reference");
            repairDeadProjectReference(this.projectId);
        }
    }


    // -----------------------------------------------------------
    // - dependencies
    // -----------------------------------------------------------

    static scrum.server.project.ProjectDao projectDao;

    public static final void setProjectDao(scrum.server.project.ProjectDao projectDao) {
        GFile.projectDao = projectDao;
    }

    static scrum.server.files.FileDao fileDao;

    public static final void setFileDao(scrum.server.files.FileDao fileDao) {
        GFile.fileDao = fileDao;
    }

}